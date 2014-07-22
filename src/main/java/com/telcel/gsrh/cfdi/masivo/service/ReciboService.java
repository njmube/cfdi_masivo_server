package com.telcel.gsrh.cfdi.masivo.service;

import java.util.List;
import java.util.Set;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Credencial;
import com.telcel.gsrh.cfdi.masivo.domain.DatosPago;
import com.telcel.gsrh.cfdi.masivo.domain.DetalleRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.Solicitud;

public interface ReciboService {

	/**
	 * Obtiene la lista de empleados por localidad
	 * @param solicitud Datos de filtrado con periodo, año y clave de localidad
	 * @return Lista de empleados por localidad
	 */
	public List<Empleado> getEmpleadosTimbradoLocalidad(Solicitud solicitud);
	
	/**
	 * Obtiene la lista de archivos spool de timbrado por localidad
	 * @param solicitud Datos de filtrado con periodo, año y clave de localidad
	 * @return Lista de archivos spool de timbrado por localidad
	 */
	public List<Archivo> getArchivosTimbradoLocalidad(Solicitud solicitud);
	
	/**
	 * Obtiene las credenciales de conexión SFTP al servidor donde se encuentran los archivos Spool
	 * @return Credenciales de conexión SFTP al servidor donde se encuentran los archivos Spool
	 */
	public Credencial getCredencialAppServerColdView();
	
	/**
	 * Obtiene el detalle de percepciones de un recibo de nómina
	 * @param solicitud Datos de filtrado con periodo, año, numero de empleado
	 * @return Detalle de percepciones de un recibo de nómina
	 */
	public List<DetalleRecibo> getDetallePercepciones(Solicitud solicitud);
	
	/**
	 * Obtiene el detalle de deducciones de un recibo de nómina
	 * @param solicitud Datos de filtrado con periodo, año, numero de empleado
	 * @return Detalle de deducciones de un recibo de nómina
	 */
	public List<DetalleRecibo> getDetalleDeducciones(Solicitud solicitud);
	
	/**
	 * Obtiene los datos de pago de recibo como días de pago, periodicidad, sdi, leyenda, gravado exento
	 * @param solicitud Datos de filtrado con periodo, año, número de empleado y tipo de recibo
	 * @return Datos de pago de recibo como días de pago, periodicidad, sdi, leyenda, gravado exento
	 */
	public DatosPago getDatosPago(Solicitud solicitud);
	
	/**
	 * Obtiene los distintos tipos de recibo en base a una lista de empleados
	 * @param empleados Lista de empleados
	 * @return Distintos tipos de recibo en base a una lista de empleados
	 */
	public Set<String> getTiposRecibo(List<Empleado> empleados); 
	
	/**
	 * Obtiene la lista de empleados de acuerdo a ciertos filtros
	 * @param solicitud Datos de filtrado con period, año, número de empleado y tipo de recibo
	 * @return Lista de empleados de acuerdo a ciertos filtros
	 */
	public List<Empleado> getEmpleadosTimbradoEnvio(SolicitudEnvioRecibo solicitud);
}
