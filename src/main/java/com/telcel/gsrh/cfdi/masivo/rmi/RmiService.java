package com.telcel.gsrh.cfdi.masivo.rmi;

import java.util.List;

import com.telcel.gsrh.cfdi.masivo.domain.ResultadoEnvio;
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
	 * Genera y envía los recibos en base a los parámetros de filtrado
	 * @param solicitud Parámetros de filtrado
	 * @return ResultadoEnvio Listas con los empleados a los que se les envío correctamente y con error el recibo
	 */
	public ResultadoEnvio generarEnviarRecibos(SolicitudEnvioRecibo solicitud);
}
