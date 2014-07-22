package com.telcel.gsrh.cfdi.masivo.mapper;

import java.util.List;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Solicitud;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;

public interface ArchivoMapper {

	public List<Archivo> getArchivosTimbradoLocalidad(Solicitud solicitud);
	
	public List<Archivo> getArchivosTimbradoEnvio(SolicitudEnvioRecibo solicitud);
}
