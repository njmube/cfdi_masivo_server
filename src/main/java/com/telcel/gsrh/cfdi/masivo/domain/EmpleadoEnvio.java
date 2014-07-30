package com.telcel.gsrh.cfdi.masivo.domain;

import java.io.Serializable;

public class EmpleadoEnvio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numEmpleado = 0;
	private String nbEmpleado = "";
	private String emailEnvio = "";
	private String msg = "";
	
	public int getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	public String getNbEmpleado() {
		return nbEmpleado;
	}
	public void setNbEmpleado(String nbEmpleado) {
		this.nbEmpleado = nbEmpleado;
	}
	public String getEmailEnvio() {
		return emailEnvio;
	}
	public void setEmailEnvio(String emailEnvio) {
		this.emailEnvio = emailEnvio;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "EmpleadoEnvio [numEmpleado=" + numEmpleado + ", nbEmpleado="
				+ nbEmpleado + ", emailEnvio=" + emailEnvio + ", msg=" + msg
				+ "]";
	}
}
