package com.telcel.gsrh.cfdi.masivo.domain;

public class DetalleRecibo {

	private String tipo = "";
	private String concepto = "";
	private Double unidades = 0.00d;
	private Double percepciones = 0.00d;
	private Double deducciones = 0.00d;
	private Double saldoAnterior = 0.00d;
	private Double movimientos = 0.00d;
	private Double saldoActual = 0.00d;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public Double getUnidades() {
		return unidades;
	}
	public void setUnidades(Double unidades) {
		this.unidades = unidades;
	}
	public Double getPercepciones() {
		return percepciones;
	}
	public void setPercepciones(Double percepciones) {
		this.percepciones = percepciones;
	}
	public Double getDeducciones() {
		return deducciones;
	}
	public void setDeducciones(Double deducciones) {
		this.deducciones = deducciones;
	}
	public Double getSaldoAnterior() {
		return saldoAnterior;
	}
	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	public Double getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(Double movimientos) {
		this.movimientos = movimientos;
	}
	public Double getSaldoActual() {
		return saldoActual;
	}
	public void setSaldoActual(Double saldoActual) {
		this.saldoActual = saldoActual;
	}
	
	@Override
	public String toString() {
		return "DetalleRecibo [tipo=" + tipo + ", concepto=" + concepto
				+ ", unidades=" + unidades + ", percepciones=" + percepciones
				+ ", deducciones=" + deducciones + ", saldoAnterior="
				+ saldoAnterior + ", movimientos=" + movimientos
				+ ", saldoActual=" + saldoActual + "]";
	}
}
