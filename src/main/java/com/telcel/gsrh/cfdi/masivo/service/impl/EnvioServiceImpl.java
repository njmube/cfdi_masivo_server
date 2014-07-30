package com.telcel.gsrh.cfdi.masivo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.cfdi.masivo.domain.EnvioMensaje;
import com.telcel.gsrh.cfdi.masivo.domain.MensajeError;
import com.telcel.gsrh.cfdi.masivo.mapper.EnvioMapper;
import com.telcel.gsrh.cfdi.masivo.service.EnvioService;

@Service("envioService")
public class EnvioServiceImpl implements EnvioService {

	@Autowired
	private EnvioMapper envioMapper;
	
	public int getIdEnvio() {
		return envioMapper.getIdEnvio();
	}

	public void putEnvioMensaje(EnvioMensaje envioMensaje) {
		envioMapper.putEnvioMensaje(envioMensaje);
	}

	public void updEnvioMensaje(int idEnvio) {
		envioMapper.updEnvioMensaje(idEnvio);;
	}

	public void updEnvioMensajeError(MensajeError mensajeError) {
		envioMapper.updEnvioMensajeError(mensajeError);
	}
}
