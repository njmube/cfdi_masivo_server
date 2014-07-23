package com.telcel.gsrh.cfdi.masivo.domain;

public class EnvioMensaje {
	private int idEnvio = 0;
	private String destinatario = "";
	private String asunto = "";
	private String mensaje = "";
	
	public EnvioMensaje() {
		
	}
	
	public EnvioMensaje(int idEnvio, String destinatario, String asunto, String mensaje) {
		this.idEnvio = idEnvio;
		this.destinatario = destinatario;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	
	public int getIdEnvio() {
		return idEnvio;
	}
	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}