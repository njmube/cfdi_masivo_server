package com.telcel.gsrh.cfdi.masivo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboEnvio;
import com.telcel.gsrh.cfdi.masivo.service.ArchivoService;
import com.telcel.gsrh.cfdi.masivo.service.ReciboService;
import com.telcel.gsrh.cfdi.masivo.util.GestorArchivos;

public class EnvioReciboAction implements Callable<Boolean> {

	@Autowired
	private ReciboService reciboService;
	@Autowired
	private ArchivoService archivoService;
	
	private ReciboEnvio recibo;
	private GestorArchivos gestorArchivos;
	
	private String msg = "";
	private SolicitudEnvioRecibo solicitud;
	private static Logger logger = Logger.getLogger(EnvioReciboAction.class);
	
	public Boolean call() throws Exception {
		try {
			if(solicitud.getNoEmpleado() != -1) {
				procesarRecibosPorEmpleado();
			} else if(solicitud.getRegion() != -1) {
				procesarRecibosPorRegion();
			}
			
			logger.info("Solicitud procesada exitosamente");
		} catch(Exception ex) {
			msg = "Error al procesar su solicitud: " + ex.getMessage();
			logger.error(ex.toString());
		}
		
		return true;
	}
	
	private void procesarRecibosPorEmpleado() throws Exception {
		List<Empleado> empleados = reciboService.getEmpleadosTimbradoEnvio(solicitud);
		
		if(solicitud.getFormato().equals("pdf"))
			generarRecibos(empleados);
		else if(solicitud.getFormato().equals("xml"))
			generarRecibosEmpleadoXml(empleados);
	}
	
	private void procesarRecibosPorRegion() throws Exception {
		List<Empleado> empleados = null;
		
		if(solicitud.getFormato().equals("pdf")) {
			empleados = reciboService.getEmpleadosTimbradoEnvio(solicitud);
			generarRecibos(empleados);
		} else if(solicitud.getFormato().equals("xml"))
			generarRecibosRegionXml();
	}
	
	private void generarRecibos(List<Empleado> empleados) throws Exception {
		for(Empleado empleado : empleados) {
			recibo.setEmpleado(empleado);
			recibo.generarRecibo();
		}
	}
	
	private void generarRecibos(Archivo archivo) throws Exception {
		recibo.generarRecibo(archivo, solicitud);
	}
	
	private void generarRecibosEmpleadoXml(List<Empleado> empleados) throws Exception {
		List<Archivo> archivos = obtenerArchivosTimbrado();
		
		for(int i = 0; i < archivos.size(); i++) {
			Archivo archivo = archivos.get(i);
			List<Empleado> empleadosArchivo = obtenerEmpleadosArchivo(archivo, empleados);
		
			generarRecibos(empleadosArchivo);
		}
	}
	
	private void generarRecibosRegionXml() throws Exception {
		List<Archivo> archivos = obtenerArchivosTimbrado();
		
		for(Archivo archivo : archivos) {
			generarRecibos(archivo);
		}
	}
	
	private List<Archivo> obtenerArchivosTimbrado() {
		List<Archivo> archivos = archivoService.getArchivosTimbradoEnvio(solicitud);
		gestorArchivos.transferirArchivos(archivos);
		
		return archivos;
	}

	private List<Empleado> obtenerEmpleadosArchivo(Archivo archivo, List<Empleado> empleados) {
		List<Empleado> empleadosArchivo = new ArrayList<Empleado>();
		
		for(Empleado empleado : empleados) {
			if(empleado.getNbArchivo().equals(archivo.getNbArchivo())) {
				empleadosArchivo.add(empleado);
			}
		}
		
		return empleadosArchivo;
	}
	
	public void setSolicitud(SolicitudEnvioRecibo solicitud) {
		this.solicitud = solicitud;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setGestorArchivos(GestorArchivos gestorArchivos) {
		this.gestorArchivos = gestorArchivos;
	}

	public void setRecibo(ReciboEnvio recibo) {
		this.recibo = recibo;
	}
}
