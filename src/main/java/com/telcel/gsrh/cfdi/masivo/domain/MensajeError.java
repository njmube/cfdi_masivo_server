package com.telcel.gsrh.cfdi.masivo.domain;

import java.io.Serializable;

public class MensajeError implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEnvio = 0;
	private String msgError = "";
	
	public int getIdEnvio() {
		return idEnvio;
	}
	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}
	public String getMsgError() {
		return msgError;
	}
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
}
