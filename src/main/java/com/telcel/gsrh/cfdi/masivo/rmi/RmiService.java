package com.telcel.gsrh.cfdi.masivo.rmi;

import java.util.List;

import com.telcel.gsrh.cfdi.masivo.domain.ResultadoEnvio;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;

public interface RmiService {

	/**
	 * Genera los recibos de una localidad en un periodo y a�o determinado
	 * @param solicitud Datos de la solicitud
	 * @return Lista de arzhivos zip generados
	 * @throws Exception
	 */
	public List<String> generarRecibosLocalidad(SolicitudUsuario solicitud);
	
	/**
	 * Genera y env�a los recibos en base a los par�metros de filtrado
	 * @param solicitud Par�metros de filtrado
	 * @return ResultadoEnvio Listas con los empleados a los que se les env�o correctamente y con error el recibo
	 */
	public ResultadoEnvio generarEnviarRecibos(SolicitudEnvioRecibo solicitud);
}
