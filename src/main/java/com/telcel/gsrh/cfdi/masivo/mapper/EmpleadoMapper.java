package com.telcel.gsrh.cfdi.masivo.mapper;

import java.util.List;

import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.Solicitud;

public interface EmpleadoMapper {

	public List<Empleado> getEmpleadosTimbradoLocalidad(Solicitud solicitud);
	
	public List<Empleado> getEmpleadosTimbradoEnvio(SolicitudEnvioRecibo solicitud);
}
