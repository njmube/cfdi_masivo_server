package com.telcel.gsrh.cfdi.masivo.recibo;

import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;

public interface ReciboLocalidad {

	public void generarRecibo() throws Exception;
	
	public void setEmpleado(Empleado empleado);
	
	public void setSolicitudUsuario(SolicitudUsuario solicitudUsuario);
}
