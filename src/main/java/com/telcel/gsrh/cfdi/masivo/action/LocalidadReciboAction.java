package com.telcel.gsrh.cfdi.masivo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboLocalidad;
import com.telcel.gsrh.cfdi.masivo.service.ArchivoService;
import com.telcel.gsrh.cfdi.masivo.service.ReciboService;
import com.telcel.gsrh.cfdi.masivo.util.GestorArchivos;
import com.telcel.gsrh.cfdi.masivo.util.Utils;

public class LocalidadReciboAction implements Callable<List<String>> {
	@Autowired
	private ReciboService reciboService;
	@Autowired
	private ArchivoService archivoService;
	
	private String msg = "";
	private List<String> archivosZip;
	
	private Utils utils;
	private SolicitudUsuario solicitud;
	private GestorArchivos gestorArchivos;
	private ReciboLocalidad recibo;
	
	private static Logger logger = Logger.getLogger(LocalidadReciboAction.class);
	
	public List<String> call() throws Exception {
		logger.info(solicitud);
		List<Empleado> empleados = null;;
		Set<String> tiposRecibo = null;
		List<String> archivosSolicitud = utils.getArchivosPeriodoAnnioLocalidadFormato(solicitud);
		
		logger.info("archivosSolicitud " + archivosSolicitud.size());
		
		if(archivosSolicitud.isEmpty()) {
			try {
				empleados = reciboService.getEmpleadosTimbradoLocalidad(solicitud);
				tiposRecibo = reciboService.getTiposRecibo(empleados);
				
				if(solicitud.getFormato().equals("pdf"))
					generarRecibos(empleados);
				else if(solicitud.getFormato().equals("xml"))
					generarRecibosXml(empleados);
				
				archivosSolicitud = utils.comprimirProductos(tiposRecibo, solicitud);
				
				logger.info("Solicitud procesada exitosamente");
			} catch(Exception ex) {
				msg = "Error al procesar su solicitud: " + ex.getMessage();
				logger.error(ex.toString());
			}
		}
		
		return archivosSolicitud;
	}
	
	private void generarRecibos(List<Empleado> empleados) throws Exception {
		for(Empleado empleado : empleados) {
			recibo.setEmpleado(empleado);
			recibo.setSolicitudUsuario(solicitud);
			recibo.generarRecibo();
		}
	}
	
	private void generarRecibosXml(List<Empleado> empleados) throws Exception {
		List<Archivo> archivosLocalidad = obtenerArchivosLocalidad();
		
		for(int i = 0; i < archivosLocalidad.size(); i++) {
			Archivo archivo = archivosLocalidad.get(i);
			List<Empleado> empleadosArchivo = obtenerEmpleadosArchivo(archivo, empleados);
		
			generarRecibos(empleadosArchivo);
		}
	}
	
	private List<Archivo> obtenerArchivosLocalidad() {
		List<Archivo> archivos = archivoService.getArchivosTimbradoLocalidad(solicitud);
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
	
	public void setRecibo(ReciboLocalidad recibo) {
		this.recibo = recibo;
	}
	
	public void setSolicitud(SolicitudUsuario solicitud) {
		this.solicitud = solicitud;
	}
	
	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<String> getArchivosZip() {
		return archivosZip;
	}

	public void setGestorArchivos(GestorArchivos gestorArchivos) {
		this.gestorArchivos = gestorArchivos;
	}
}
