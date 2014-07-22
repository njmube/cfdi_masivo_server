package com.telcel.gsrh.cfdi.masivo.rmi.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.telcel.gsrh.cfdi.masivo.action.EnvioReciboAction;
import com.telcel.gsrh.cfdi.masivo.action.LocalidadReciboAction;
import com.telcel.gsrh.cfdi.masivo.config.Configuracion;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;

import com.telcel.gsrh.cfdi.masivo.recibo.ReciboEnvio;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboLocalidad;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboEnvioPdf;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboEnvioXml;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboLocalidadPdf;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboLocalidadXml;
import com.telcel.gsrh.cfdi.masivo.rmi.RmiService;

public class RmiServiceImpl implements RmiService {
	private static ApplicationContext context;
	private static ThreadPoolTaskExecutor taskExecutor;
	private Logger logger = Logger.getLogger(RmiServiceImpl.class);
	
	public RmiServiceImpl() {
		
	}
	
	static {
		context = new AnnotationConfigApplicationContext(Configuracion.class);
		taskExecutor = context.getBean("threadPoolTaskExecutor", ThreadPoolTaskExecutor.class);
	}
	
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
	
	public void generarEnviarRecibos(SolicitudEnvioRecibo solicitud) {
		EnvioReciboAction envioReciboAction = context.getBean("envioReciboAction", EnvioReciboAction.class);
		ReciboEnvio reciboEnvioPdf = context.getBean("reciboEnvioPdf", ReciboEnvioPdf.class);
		ReciboEnvio reciboEnvioXml = context.getBean("reciboEnvioXml", ReciboEnvioXml.class);
		
		solicitud.setFormato("xml");
		envioReciboAction.setSolicitud(solicitud);
		envioReciboAction.setRecibo(reciboEnvioXml);
		
		logger.info("Procedemos a generar los recibos xml");
		
		FutureTask<Boolean> futureResults = (FutureTask<Boolean>)taskExecutor.submit(envioReciboAction);
		Boolean banderaXml = false;
		Boolean banderaPdf = false;
		
		try {
			banderaXml = futureResults.get();
			
			if(banderaXml) {//No hubo errores en la generación de xml
				logger.info("Procedemos a generar los recibos pdf");
				
				solicitud.setFormato("pdf");
				envioReciboAction.setSolicitud(solicitud);
				envioReciboAction.setRecibo(reciboEnvioPdf);
				
				futureResults = (FutureTask<Boolean>)taskExecutor.submit(envioReciboAction);
				banderaPdf = futureResults.get();
				
				if(banderaPdf) {
					logger.info("Procedemos a enviar los recibos");
				}
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			envioReciboAction.setMsg(e.getMessage());
		} catch (ExecutionException e) {
			logger.error(e.getMessage());
			envioReciboAction.setMsg(e.getMessage());
		} finally {
			logger.info("proceso finalizado con banderaXml: " + banderaXml + "; banderaPdf " + banderaPdf);
		}
	}
}
