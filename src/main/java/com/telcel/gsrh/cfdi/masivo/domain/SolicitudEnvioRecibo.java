package com.telcel.gsrh.cfdi.masivo.domain;

import java.io.Serializable;

public class SolicitudEnvioRecibo extends Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	private int noEmpleado = -1;
	private int region = -1;
	private int tpRecibo = -1;
	private String formato = "";
	
	public int getNoEmpleado() {
		return noEmpleado;
	}
	public void setNoEmpleado(int noEmpleado) {
		this.noEmpleado = noEmpleado;
	}
	public int getRegion() {
		return region;
	}
	public void setRegion(int region) {
		this.region = region;
	}
	public int getTpRecibo() {
		return tpRecibo;
	}
	public void setTpRecibo(int tpRecibo) {
		this.tpRecibo = tpRecibo;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	@Override
	public String toString() {
		return "SolicitudEnvioRecibo [getNoEmpleado()=" + getNoEmpleado()
				+ ", getRegion()=" + getRegion() + ", getTpRecibo()="
				+ getTpRecibo() + ", getFormato()=" + getFormato()
				+ ", getAnnio()=" + getAnnio() + ", getPeriodo()="
				+ getPeriodo() + ", getRfcEmpresa()=" + getRfcEmpresa() + "]";
	}
}
