package com.telcel.gsrh.cfdi.masivo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultadoEnvio implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<EmpleadoEnvio> empleadosEnvioCorrecto = new ArrayList<EmpleadoEnvio>();
	private List<EmpleadoEnvio> empleadosEnvioError = new ArrayList<EmpleadoEnvio>();
	
	public List<EmpleadoEnvio> getEmpleadosEnvioCorrecto() {
		return empleadosEnvioCorrecto;
	}
	public List<EmpleadoEnvio> getEmpleadosEnvioError() {
		return empleadosEnvioError;
	}
	
	public void agregarEmpleadoEnvioCorrecto(EmpleadoEnvio empleadoEnvio) {
		empleadosEnvioCorrecto.add(empleadoEnvio);
	}
	
	public void agregarEmpleadoEnvioError(EmpleadoEnvio empleadoEnvio) {
		empleadosEnvioError.add(empleadoEnvio);
	}
}
