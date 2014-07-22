package com.telcel.gsrh.cfdi.masivo.domain;

import java.io.Serializable;

public class DatosPago implements Serializable {
	private static final long serialVersionUID = -6809241166554624974L;
	private int diasPagados = 0;
	private String periodicidad = "";
	private Double sdi = 0.00d;
	private Double gravado = 0.00d;
	private Double exento = 0.00d;
	private String leyenda = "";
	
	public int getDiasPagados() {
		return diasPagados;
	}
	public void setDiasPagados(int diasPagados) {
		this.diasPagados = diasPagados;
	}
	public String getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}
	public Double getSdi() {
		return sdi;
	}
	public void setSdi(Double sdi) {
		this.sdi = sdi;
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
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}
	
	@Override
	public String toString() {
		return "DatosPago [diasPagados=" + diasPagados + ", periodicidad="
				+ periodicidad + ", sdi=" + sdi + ", gravado=" + gravado
				+ ", exento=" + exento + ", leyenda=" + leyenda + "]";
	}
}
