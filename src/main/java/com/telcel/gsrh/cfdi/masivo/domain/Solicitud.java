package com.telcel.gsrh.cfdi.masivo.domain;

import java.io.Serializable;

public abstract class Solicitud implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int annio = 0;
	private int periodo = 0;
	private String rfcEmpresa = "";
	
	public int getAnnio() {
		return annio;
	}
	public void setAnnio(int annio) {
		this.annio = annio;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public String getRfcEmpresa() {
		return rfcEmpresa;
	}
	public void setRfcEmpresa(String rfcEmpresa) {
		this.rfcEmpresa = rfcEmpresa;
	}
}
