package com.telcel.gsrh.cfdi.masivo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Credencial;
import com.telcel.gsrh.cfdi.masivo.domain.DatosPago;
import com.telcel.gsrh.cfdi.masivo.domain.DetalleRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.Solicitud;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.mapper.ArchivoMapper;
import com.telcel.gsrh.cfdi.masivo.mapper.CredencialMapper;
import com.telcel.gsrh.cfdi.masivo.mapper.DetalleReciboMapper;
import com.telcel.gsrh.cfdi.masivo.mapper.EmpleadoMapper;
import com.telcel.gsrh.cfdi.masivo.service.ReciboService;

@Service("reciboService")
public class ReciboServiceImpl implements ReciboService {

	@Autowired
	private ArchivoMapper archivoMapper;
	
	@Autowired
	private CredencialMapper credencialMapper;
	
	@Autowired
	private DetalleReciboMapper detalleReciboMapper;
	
	@Autowired
	private EmpleadoMapper empleadoMapper;
	
	public List<Empleado> getEmpleadosTimbradoLocalidad(Solicitud solicitud) {
		return empleadoMapper.getEmpleadosTimbradoLocalidad(solicitud);
	}
	
	public List<Archivo> getArchivosTimbradoLocalidad(Solicitud solicitud) {
		return archivoMapper.getArchivosTimbradoLocalidad(solicitud);
	}
	
	public Credencial getCredencialAppServerColdView() {
		return credencialMapper.getCredencialAppServerColdView();
	}
	
	public List<DetalleRecibo> getDetallePercepciones(Solicitud solicitud) {
		return detalleReciboMapper.getDetallePercepciones(solicitud);
	}
	
	public List<DetalleRecibo> getDetalleDeducciones(Solicitud solicitud) {
		return detalleReciboMapper.getDetalleDeducciones(solicitud);
	}
	
	public DatosPago getDatosPago(Solicitud solicitud) {
		return detalleReciboMapper.getDatosPago(solicitud);
	}

	public Set<String> getTiposRecibo(List<Empleado> empleados) {
		Set<String> tiposRecibo = new HashSet<String>();
		
		if(empleados != null) {
			for(Empleado empleado : empleados) {
				tiposRecibo.add(empleado.getProducto());
			}
		}
		
		return tiposRecibo;
	}

	public List<Empleado> getEmpleadosTimbradoEnvio(SolicitudEnvioRecibo solicitud) {
		return empleadoMapper.getEmpleadosTimbradoEnvio(solicitud);
	}
}
