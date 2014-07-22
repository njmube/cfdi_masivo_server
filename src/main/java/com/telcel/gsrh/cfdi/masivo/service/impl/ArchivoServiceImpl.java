package com.telcel.gsrh.cfdi.masivo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Solicitud;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.mapper.ArchivoMapper;
import com.telcel.gsrh.cfdi.masivo.service.ArchivoService;

@Service("archivoService")
public class ArchivoServiceImpl implements ArchivoService {
	
	@Autowired
	private ArchivoMapper archivoMapper;
	
	public List<Archivo> getArchivosTimbradoLocalidad(Solicitud solicitud) {
		return archivoMapper.getArchivosTimbradoLocalidad(solicitud);
	}
	
	public List<Archivo> getArchivosTimbradoEnvio(SolicitudEnvioRecibo solicitud) {
		return archivoMapper.getArchivosTimbradoEnvio(solicitud);
	}
}
