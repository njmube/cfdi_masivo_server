package com.telcel.gsrh.cfdi.masivo.mapper;

import com.telcel.gsrh.cfdi.masivo.domain.EnvioMensaje;
import com.telcel.gsrh.cfdi.masivo.domain.MensajeError;

public interface EnvioMapper {

	public int getIdEnvio();
	
	public void putEnvioMensaje(EnvioMensaje envioMensaje);
	
	public void updEnvioMensaje(int idEnvio);
	
	public void updEnvioMensajeError(MensajeError mensajeError);
}
