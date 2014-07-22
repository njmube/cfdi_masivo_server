package com.telcel.gsrh.cfdi.masivo.recibo.impl;

import java.io.File;

import org.apache.log4j.Logger;

import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboLocalidad;
import com.telcel.gsrh.cfdi.masivo.util.Utils;

public class ReciboLocalidadXml implements ReciboLocalidad {
	private Utils utils;
	private Empleado empleado;
	private SolicitudUsuario solicitudUsuario;
	private static Logger logger = Logger.getLogger(ReciboLocalidadXml.class);
	
	public void generarRecibo() throws Exception {
		logger.info("empleado: " + empleado.getNumEmpleado());
		File archivoSpool = utils.getArchivoSpool(empleado.getNbArchivo());
		
		String xmlRecibo = utils.encontrarXmlEmpleado(archivoSpool, empleado);
		
		//Genera archivo XML
		utils.generarDocumentoXmlEmpleado(xmlRecibo, empleado, solicitudUsuario.getFormato());
	}
	
	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void setSolicitudUsuario(SolicitudUsuario solicitudUsuario) {
		this.solicitudUsuario = solicitudUsuario;
	}
}
