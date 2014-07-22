package com.telcel.gsrh.cfdi.masivo.rmi;

import java.util.List;

import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;

public interface RmiService {

	/**
	 * Genera los recibos de una localidad en un periodo y año determinado
	 * @param solicitud Datos de la solicitud
	 * @return Lista de arzhivos zip generados
	 * @throws Exception
	 */
	public List<String> generarRecibosLocalidad(SolicitudUsuario solicitud);
	
	/**
	 * Genera y envía los recibos de nómina Telcel en base a ciertos parámetros de filtrado
	 * @param solicitud Parámetros de filtrado
	 */
	public void generarEnviarRecibos(SolicitudEnvioRecibo solicitud);
}
