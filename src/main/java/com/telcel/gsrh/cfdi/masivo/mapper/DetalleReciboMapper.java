package com.telcel.gsrh.cfdi.masivo.mapper;

import java.util.List;

import com.telcel.gsrh.cfdi.masivo.domain.DatosPago;
import com.telcel.gsrh.cfdi.masivo.domain.DetalleRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.Solicitud;

public interface DetalleReciboMapper {

	public List<DetalleRecibo> getDetallePercepciones(Solicitud solicitud);
	
	public List<DetalleRecibo> getDetalleDeducciones(Solicitud solicitud);
	
	public DatosPago getDatosPago(Solicitud solicitud);
}
