package com.telcel.gsrh.cfdi.masivo.action;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.EnvioMensaje;
import com.telcel.gsrh.cfdi.masivo.domain.MensajeError;
import com.telcel.gsrh.cfdi.masivo.service.EnvioService;
import com.telcel.gsrh.cfdi.masivo.util.Utils;
import com.telcel.mail.EnviaMail;

public class EnvioAction implements Callable<Boolean> {
	@Autowired
	private EnvioService envioService;
	
	private Empleado empleado;
	private Utils utils;
	private String msg;
	
	private static Logger logger = Logger.getLogger(EnvioAction.class);
	
	@Override
	public Boolean call() {
		try {
			if((empleado.getEmail2() != null) && (!empleado.getEmail2().equals("---")))
				enviarMensaje(empleado.getEmail2());
			
			else if((empleado.getEmail1() != null) && (!empleado.getEmail1().equals("---")))
					enviarMensaje(empleado.getEmail1());
		} catch(Exception ex) {
			msg = ex.getMessage();
			return false;
		}
		
		return true;
	}
	
	private void enviarMensaje(String destinatario) throws Exception {
		String asunto = utils.getValueProperties("correo.sujeto").replace("param_concepto", empleado.getProducto()).replace("param_periodo", empleado.getPeriodo().toLowerCase());;
		String mensaje = utils.getValueProperties("correo.mensaje").replace("param_nbDestinatario", empleado.getNbEmpleado()).replace("param_concepto", empleado.getProducto()).replace("param_periodo", empleado.getPeriodo().toLowerCase());
		String rutaArchivoPdf = utils.getRutaArchivoPdf(String.valueOf(empleado.getNumEmpleado()), empleado.getProducto(), empleado.getQuincena(), empleado.getAnnioPago());
		String rutaArchivoXml = utils.getRutaArchivoXml(String.valueOf(empleado.getNumEmpleado()), empleado.getProducto(), empleado.getQuincena(), empleado.getAnnioPago());
		int idEnvio = 0;
		
		try{
			idEnvio = envioService.getIdEnvio();
			
			EnvioMensaje envioMensaje = new EnvioMensaje(idEnvio, destinatario, asunto, mensaje);
			envioService.putEnvioMensaje(envioMensaje);
			
			EnviaMail mail = new EnviaMail(utils.getValueProperties("correo.usuario"), utils.getValueProperties("correo.contrasennia"));
			mail.setFrom(utils.getValueProperties("correo.emisor"));
			mail.setSubject(asunto);
			mail.setTo(destinatario);
			//mail.setTo("palomas.ticas@gmail.com");
			
			mail.addContent(mensaje);
			
			if(utils.existeArchivo(rutaArchivoPdf))
				mail.addAttach(rutaArchivoPdf);
			else
				logger.error("Archivo pdf no encontrado " + rutaArchivoPdf);
				
			if(utils.existeArchivo(rutaArchivoXml))
				mail.addAttach(rutaArchivoXml);
			else
				logger.error("Archivo xml no encontrado " + rutaArchivoXml);
				
			mail.sendMultipart();
			
			envioService.updEnvioMensaje(idEnvio);
			
			logger.info("Mensaje enviado a " + destinatario);
		} catch(Exception e) {
			msg = e.toString().equals(null) ? "" : e.toString();
			msg = "Error al enviar mensaje a la cuenta " + destinatario + ":\t" + msg;
			int lenError = msg.length();
			String msgError = lenError < 2000 ? msg.substring(0, lenError) : msg.substring(0, 2000);
			
			MensajeError error = new MensajeError();
			error.setIdEnvio(idEnvio);
			error.setMsgError(msgError);
			
			envioService.updEnvioMensajeError(error);
			throw new Exception(msgError);
		}
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public String getMsg() {
		return msg;
	}
}
