package com.telcel.gsrh.cfdi.masivo.domain;

public class Credencial {

	private String ip = "";
	private String usuario = "";
	private String contrasennia = "";
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasennia() {
		return contrasennia;
	}
	public void setContrasennia(String contrasennia) {
		this.contrasennia = contrasennia;
	}
	
	@Override
	public String toString() {
		return "Credencial [ip=" + ip + ", usuario=" + usuario
				+ ", contrasennia=" + contrasennia + "]";
	}
}
