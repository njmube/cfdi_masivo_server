package com.telcel.gsrh.cfdi.masivo.domain;

import java.io.Serializable;

public class SolicitudNominaEmpleado extends Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tpRecibo = 0;
	private int numEmpleado = 0;
	
	public int getTpRecibo() {
		return tpRecibo;
	}
	public void setTpRecibo(int tpRecibo) {
		this.tpRecibo = tpRecibo;
	}
	public int getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	
	@Override
	public String toString() {
		return "SolicitudNominaEmpleado [tpRecibo=" + tpRecibo
				+ ", numEmpleado=" + numEmpleado + "]";
	}
}
