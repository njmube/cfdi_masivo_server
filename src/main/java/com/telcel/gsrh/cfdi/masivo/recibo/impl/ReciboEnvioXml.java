package com.telcel.gsrh.cfdi.masivo.recibo.impl;

import java.io.File;

import org.apache.log4j.Logger;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudNominaEmpleado;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboEnvio;
import com.telcel.gsrh.cfdi.masivo.util.Utils;

public class ReciboEnvioXml implements ReciboEnvio {
	private Utils utils;
	private Empleado empleado;
	private static Logger logger = Logger.getLogger(ReciboEnvioXml.class);
	
	public void generarRecibo() throws Exception {
		logger.info("empleado: " + empleado.getNumEmpleado());
		File archivoSpool = utils.getArchivoSpool(empleado.getNbArchivo());
		
		String xmlRecibo = utils.encontrarXmlEmpleado(archivoSpool, empleado);
		
		//Genera archivo XML
		utils.generarDocumentoXmlEmpleado(xmlRecibo, empleado, "xml");
	}
	
	public void generarRecibo(Archivo archivo, SolicitudEnvioRecibo solicitud) throws Exception {
		logger.info(archivo);
		utils.procesarArchivoSpool(archivo, solicitud);
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public void setSolicitudNominaEmpleado(SolicitudNominaEmpleado solicitudNominaEmpleado) {
		
	}
}
