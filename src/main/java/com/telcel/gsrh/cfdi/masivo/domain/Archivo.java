package com.telcel.gsrh.cfdi.masivo.domain;

public class Archivo {
	private String nbArchivo = "";
	private String producto = "";
	
	public String getNbArchivo() {
		return nbArchivo;
	}
	public void setNbArchivo(String nbArchivo) {
		this.nbArchivo = nbArchivo;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	@Override
	public String toString() {
		return "Archivo [nbArchivo=" + nbArchivo + ", producto=" + producto
				+ "]";
	}
}