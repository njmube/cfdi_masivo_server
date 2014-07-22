package com.telcel.gsrh.cfdi.masivo.domain;

public class GravadoExento {

	private Double gravado = 0.00d;
	private Double exento = 0.00d;
	
	public GravadoExento() {
		
	}
	public GravadoExento(Double gravado, Double exento) {
		this.gravado = gravado;
		this.exento = exento;
	}
	
	public Double getGravado() {
		return gravado;
	}
	public void setGravado(Double gravado) {
		this.gravado = gravado;
	}
	public Double getExento() {
		return exento;
	}
	public void setExento(Double exento) {
		this.exento = exento;
	}
}
