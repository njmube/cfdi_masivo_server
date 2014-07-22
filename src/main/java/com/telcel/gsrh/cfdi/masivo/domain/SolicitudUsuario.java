package com.telcel.gsrh.cfdi.masivo.domain;

import java.io.Serializable;

public class SolicitudUsuario extends Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	private String formato = "";
	private int usuarioSoliciante = 0;
	private String cveLocalidad = "";
	
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public int getUsuarioSoliciante() {
		return usuarioSoliciante;
	}
	public void setUsuarioSoliciante(int usuarioSoliciante) {
		this.usuarioSoliciante = usuarioSoliciante;
	}
	public String getCveLocalidad() {
		return cveLocalidad;
	}
	public void setCveLocalidad(String cveLocalidad) {
		this.cveLocalidad = cveLocalidad;
	}
	@Override
	public String toString() {
		return "SolicitudUsuario [getFormato()="
				+ getFormato() + ", getUsuarioSoliciante()="
				+ getUsuarioSoliciante() + ", getCveLocalidad()="
				+ getCveLocalidad() + ", getAnnio()="
				+ getAnnio() + ", getPeriodo()="
				+ getPeriodo() + ", getRfcEmpresa()="
				+ getRfcEmpresa() + "]";
	}
}
