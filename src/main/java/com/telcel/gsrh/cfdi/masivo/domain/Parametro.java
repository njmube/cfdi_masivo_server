package com.telcel.gsrh.cfdi.masivo.domain;

public class Parametro {

	private int cveParametro = 0;
	private String valorParametro = "";
	private String descripcion = "";
	
	public int getCveParametro() {
		return cveParametro;
	}
	public void setCveParametro(int cveParametro) {
		this.cveParametro = cveParametro;
	}
	public String getValorParametro() {
		return valorParametro;
	}
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
