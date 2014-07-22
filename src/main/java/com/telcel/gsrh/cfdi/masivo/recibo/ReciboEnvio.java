package com.telcel.gsrh.cfdi.masivo.recibo;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudNominaEmpleado;
import com.telcel.gsrh.cfdi.masivo.util.Utils;

public interface ReciboEnvio {

	public void generarRecibo() throws Exception;
	
	public void generarRecibo(Archivo archivo, SolicitudEnvioRecibo solicitud) throws Exception;

	public void setUtils(Utils utils);

	public void setEmpleado(Empleado empleado);

	public void setSolicitudNominaEmpleado(SolicitudNominaEmpleado solicitudNominaEmpleado);
}