package com.telcel.gsrh.cfdi.masivo.rmi.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.telcel.gsrh.cfdi.masivo.action.EnvioAction;
import com.telcel.gsrh.cfdi.masivo.action.GeneradorReciboAction;
import com.telcel.gsrh.cfdi.masivo.action.LocalidadReciboAction;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.EmpleadoEnvio;
import com.telcel.gsrh.cfdi.masivo.domain.ResultadoEnvio;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboEnvio;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboLocalidad;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboEnvioPdf;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboEnvioXml;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboLocalidadPdf;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboLocalidadXml;
import com.telcel.gsrh.cfdi.masivo.rmi.RmiService;
import com.telcel.gsrh.cfdi.masivo.service.ReciboService;

public class RmiServiceImpl implements RmiService {
	private ApplicationContext context;
	private ThreadPoolTaskExecutor taskExecutor;
	private Logger logger = Logger.getLogger(RmiServiceImpl.class);
	
	public List<String> generarRecibosLocalidad(SolicitudUsuario solicitud) {
		ReciboLocalidad reciboLocalidadPdf = context.getBean("reciboLocalidadPdf", ReciboLocalidadPdf.class);
		ReciboLocalidad reciboLocalidadXml = context.getBean("reciboLocalidadXml", ReciboLocalidadXml.class);
		
		LocalidadReciboAction localidadReciboAction = context.getBean("localidadReciboAction", LocalidadReciboAction.class);
		localidadReciboAction.setSolicitud(solicitud);
		
		//Se asigna el tipo de recibo de acuerdo al formato
		if(solicitud.getFormato().equals("pdf"))
			localidadReciboAction.setRecibo(reciboLocalidadPdf);
		
		if(solicitud.getFormato().equals("xml"))
			localidadReciboAction.setRecibo(reciboLocalidadXml);
		
		FutureTask<List<String>> futureResults = (FutureTask<List<String>>)taskExecutor.submit(localidadReciboAction);
		List<String> lista = new ArrayList<String>();
		
		try {
			lista = futureResults.get();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			localidadReciboAction.setMsg(e.getMessage());
		} catch (ExecutionException e) {
			logger.error(e.getMessage());
			localidadReciboAction.setMsg(e.getMessage());
		}
		
		return lista;
	}
	
	public ResultadoEnvio generarEnviarRecibos(SolicitudEnvioRecibo solicitud) {
		logger.info(solicitud);
		
		GeneradorReciboAction generadorReciboAction = context.getBean("generadorReciboAction", GeneradorReciboAction.class);
		Boolean banderaXml = false;
		Boolean banderaPdf = false;
		ResultadoEnvio resultadoEnvio = null;
		
		try {
			logger.info("Procedemos a generar los recibos xml");
			banderaXml = generarRecibosXml(solicitud, generadorReciboAction);
			
			if(banderaXml) {//No hubo errores en la generación de xml
				logger.info("Procedemos a generar los recibos pdf");
				banderaPdf = generarRecibosPdf(solicitud, generadorReciboAction);
				
				if(banderaPdf) {
					logger.info("Procedemos a enviar los recibos");
					resultadoEnvio = enviarRecibos(solicitud);
				}
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			generadorReciboAction.setMsg(e.getMessage());
		} catch (ExecutionException e) {
			logger.error(e.getMessage());
			generadorReciboAction.setMsg(e.getMessage());
		} finally {
			logger.info("proceso finalizado con banderaXml: " + banderaXml + "; banderaPdf " + banderaPdf);
		}
		
		return resultadoEnvio;
	}
	
	
	private boolean generarRecibosXml(SolicitudEnvioRecibo solicitud, GeneradorReciboAction generadorReciboAction) throws InterruptedException, ExecutionException {
		ReciboEnvio reciboEnvioXml = context.getBean("reciboEnvioXml", ReciboEnvioXml.class);
		Boolean banderaXml = false;
		
		solicitud.setFormato("xml");
		generadorReciboAction.setSolicitud(solicitud);
		generadorReciboAction.setRecibo(reciboEnvioXml);
		
		FutureTask<Boolean> futureResults = (FutureTask<Boolean>)taskExecutor.submit(generadorReciboAction);
		banderaXml = futureResults.get();
			
		return banderaXml;
	}
	
	private boolean generarRecibosPdf(SolicitudEnvioRecibo solicitud, GeneradorReciboAction generadorReciboAction) throws InterruptedException, ExecutionException {
		ReciboEnvio reciboEnvioPdf = context.getBean("reciboEnvioPdf", ReciboEnvioPdf.class);
		Boolean banderaPdf = false;
		
		solicitud.setFormato("pdf");
		generadorReciboAction.setSolicitud(solicitud);
		generadorReciboAction.setRecibo(reciboEnvioPdf);
		
		FutureTask<Boolean> futureResults = (FutureTask<Boolean>)taskExecutor.submit(generadorReciboAction);
		banderaPdf = futureResults.get();
		
		return banderaPdf;
	}
	
	private ResultadoEnvio enviarRecibos(SolicitudEnvioRecibo solicitud) throws InterruptedException, ExecutionException {
		ReciboService reciboService = context.getBean("reciboService", ReciboService.class);
		ResultadoEnvio resultadoEnvio = context.getBean("resultadoEnvio", ResultadoEnvio.class);
		List<Empleado> empleados = reciboService.getEmpleadosTimbradoEnvio(solicitud);
		
		Boolean banderaEnvio = false;
		vaciarListas(resultadoEnvio);
		
		if(empleados != null) {
			for(Empleado empleado : empleados) {
				EmpleadoEnvio empleadoEnvio = context.getBean("empleadoEnvio", EmpleadoEnvio.class);
				EnvioAction envioAction = context.getBean("envioAction", EnvioAction.class);
				envioAction.setEmpleado(empleado);
				
				empleadoEnvio.setNumEmpleado(empleado.getNumEmpleado());
				empleadoEnvio.setNbEmpleado(empleado.getNbEmpleado());
				empleadoEnvio.setEmailEnvio(empleado.getEmail2().equals("---") ? empleado.getEmail1() : empleado.getEmail2());
				
				FutureTask<Boolean> futureResults = (FutureTask<Boolean>)taskExecutor.submit(envioAction);
				banderaEnvio = futureResults.get();
				
				if(banderaEnvio) {
					resultadoEnvio.agregarEmpleadoEnvioCorrecto(empleadoEnvio);
				} else {
					empleadoEnvio.setMsg(envioAction.getMsg());
					resultadoEnvio.agregarEmpleadoEnvioError(empleadoEnvio);
					logger.error(envioAction.getMsg());
				}
			}
		}
		
		return resultadoEnvio;
	}
	
	private void vaciarListas(ResultadoEnvio resultadoEnvio) {
		resultadoEnvio.getEmpleadosEnvioCorrecto().clear();
		resultadoEnvio.getEmpleadosEnvioError().clear();
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
}
