package com.telcel.gsrh.cfdi.masivo.domain;

public class Empleado implements Comparable<Empleado> {

	private int numEmpleado = 0;
	private String rfcEmpleado = "";
	private String nbArchivo = "";
	private String producto = "";
	private String selloDigital = "";
	private String noCertificadoCfd = "";
	private String uuid = "";
	private String fechaExpedicion = "";
	private String fechaTimbrado = "";
	private String noCertificadoSat = "";
	private String selloSat = "";
	private String cadenaOriginal = "";
	private String codigoBarras = "";
	private String rfcEmpresa = "";
	private String razonSocialEmpresa = "";
	private String regimenFiscalEmpresa = "";
	private String tipoRegimenEmpresa = "";
	private String domicilioFiscalEmpresa = "";
	private String nbEmpleado = "";
	private String imss = "";
	private String puesto = "";
	private int diaPago = 0;
	private int mesPago = 0;
	private int annioPago = 0;
	private int quincena = 0;
	private String fechaInicialPago = "";
	private String fechaFinalPago = "";
	private String periodo = "";
	private String fechaPago = "";
	private int payrollId = 0;
	private int orgId = 0;
	private String curp = "";
	private String categoria = "";
	private String locationId = "";
	private int timePeriodId = 0;
	private int idNomina = 0;
	private String departamento = "";
	private String localidadPago = "";
	private String region = "";
	private String registroPatronal = "";
	private int claveRiesgo = 0;
	private int antiguedad = 0;
	private String ingreso = "";
	private String contrato = "";
	private String banco = "";
	private String salario = "";
	private String jornada = "";
	private String lugarEmision = "";
	private String email1 = "";
	private String email2 = "";
	
	public int getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	public String getRfcEmpleado() {
		return rfcEmpleado;
	}
	public void setRfcEmpleado(String rfcEmpleado) {
		this.rfcEmpleado = rfcEmpleado;
	}
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
	public String getSelloDigital() {
		return selloDigital;
	}
	public void setSelloDigital(String selloDigital) {
		this.selloDigital = selloDigital;
	}
	public String getNoCertificadoCfd() {
		return noCertificadoCfd;
	}
	public void setNoCertificadoCfd(String noCertificadoCfd) {
		this.noCertificadoCfd = noCertificadoCfd;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFechaExpedicion() {
		return fechaExpedicion;
	}
	public void setFechaExpedicion(String fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	public String getFechaTimbrado() {
		return fechaTimbrado;
	}
	public void setFechaTimbrado(String fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}
	public String getNoCertificadoSat() {
		return noCertificadoSat;
	}
	public void setNoCertificadoSat(String noCertificadoSat) {
		this.noCertificadoSat = noCertificadoSat;
	}
	public String getSelloSat() {
		return selloSat;
	}
	public void setSelloSat(String selloSat) {
		this.selloSat = selloSat;
	}
	public String getCadenaOriginal() {
		return cadenaOriginal;
	}
	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getRfcEmpresa() {
		return rfcEmpresa;
	}
	public void setRfcEmpresa(String rfcEmpresa) {
		this.rfcEmpresa = rfcEmpresa;
	}
	public String getRazonSocialEmpresa() {
		return razonSocialEmpresa;
	}
	public void setRazonSocialEmpresa(String razonSocialEmpresa) {
		this.razonSocialEmpresa = razonSocialEmpresa;
	}
	public String getRegimenFiscalEmpresa() {
		return regimenFiscalEmpresa;
	}
	public void setRegimenFiscalEmpresa(String regimenFiscalEmpresa) {
		this.regimenFiscalEmpresa = regimenFiscalEmpresa;
	}
	public String getTipoRegimenEmpresa() {
		return tipoRegimenEmpresa;
	}
	public void setTipoRegimenEmpresa(String tipoRegimenEmpresa) {
		this.tipoRegimenEmpresa = tipoRegimenEmpresa;
	}
	public String getDomicilioFiscalEmpresa() {
		return domicilioFiscalEmpresa;
	}
	public void setDomicilioFiscalEmpresa(String domicilioFiscalEmpresa) {
		this.domicilioFiscalEmpresa = domicilioFiscalEmpresa;
	}
	public String getNbEmpleado() {
		return nbEmpleado;
	}
	public void setNbEmpleado(String nbEmpleado) {
		this.nbEmpleado = nbEmpleado;
	}
	public String getImss() {
		return imss;
	}
	public void setImss(String imss) {
		this.imss = imss;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public int getDiaPago() {
		return diaPago;
	}
	public void setDiaPago(int diaPago) {
		this.diaPago = diaPago;
	}
	public int getMesPago() {
		return mesPago;
	}
	public void setMesPago(int mesPago) {
		this.mesPago = mesPago;
	}
	public int getAnnioPago() {
		return annioPago;
	}
	public void setAnnioPago(int annioPago) {
		this.annioPago = annioPago;
	}
	public int getQuincena() {
		return quincena;
	}
	public void setQuincena(int quincena) {
		this.quincena = quincena;
	}
	public String getFechaInicialPago() {
		return fechaInicialPago;
	}
	public void setFechaInicialPago(String fechaInicialPago) {
		this.fechaInicialPago = fechaInicialPago;
	}
	public String getFechaFinalPago() {
		return fechaFinalPago;
	}
	public void setFechaFinalPago(String fechaFinalPago) {
		this.fechaFinalPago = fechaFinalPago;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public int getPayrollId() {
		return payrollId;
	}
	public void setPayrollId(int payrollId) {
		this.payrollId = payrollId;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public int getTimePeriodId() {
		return timePeriodId;
	}
	public void setTimePeriodId(int timePeriodId) {
		this.timePeriodId = timePeriodId;
	}
	public int getIdNomina() {
		return idNomina;
	}
	public void setIdNomina(int idNomina) {
		this.idNomina = idNomina;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getLocalidadPago() {
		return localidadPago;
	}
	public void setLocalidadPago(String localidadPago) {
		this.localidadPago = localidadPago;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegistroPatronal() {
		return registroPatronal;
	}
	public void setRegistroPatronal(String registroPatronal) {
		this.registroPatronal = registroPatronal;
	}
	public int getClaveRiesgo() {
		return claveRiesgo;
	}
	public void setClaveRiesgo(int claveRiesgo) {
		this.claveRiesgo = claveRiesgo;
	}
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	public String getIngreso() {
		return ingreso;
	}
	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public String getJornada() {
		return jornada;
	}
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}
	public String getLugarEmision() {
		return lugarEmision;
	}
	public void setLugarEmision(String lugarEmision) {
		this.lugarEmision = lugarEmision;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	public int compareTo(Empleado nuevoEmpleado) {
		int comparador = this.region.compareTo(nuevoEmpleado.getRegion());
		Integer noEmpPrev = new Integer(this.numEmpleado);
		Integer noEmpNuevo = new Integer(nuevoEmpleado.getNumEmpleado());
		
		if(comparador == 0)
			comparador = noEmpPrev.compareTo(noEmpNuevo);
				
		return comparador;
	}
	
	public String toString() {
		return "Empleado [numEmpleado=" + numEmpleado + ", rfcEmpleado="
				+ rfcEmpleado + ", nbArchivo=" + nbArchivo + ", producto="
				+ producto + ", selloDigital=" + selloDigital
				+ ", noCertificadoCfd=" + noCertificadoCfd + ", uuid=" + uuid
				+ ", fechaExpedicion=" + fechaExpedicion + ", fechaTimbrado="
				+ fechaTimbrado + ", noCertificadoSat=" + noCertificadoSat
				+ ", selloSat=" + selloSat + ", cadenaOriginal="
				+ cadenaOriginal + ", codigoBarras=" + codigoBarras
				+ ", rfcEmpresa=" + rfcEmpresa + ", razonSocialEmpresa="
				+ razonSocialEmpresa + ", regimenFiscalEmpresa="
				+ regimenFiscalEmpresa + ", tipoRegimenEmpresa="
				+ tipoRegimenEmpresa + ", domicilioFiscalEmpresa="
				+ domicilioFiscalEmpresa + ", nbEmpleado=" + nbEmpleado
				+ ", imss=" + imss + ", puesto=" + puesto + ", diaPago="
				+ diaPago + ", mesPago=" + mesPago + ", annioPago=" + annioPago
				+ ", quincena=" + quincena + ", fechaInicialPago="
				+ fechaInicialPago + ", fechaFinalPago=" + fechaFinalPago
				+ ", periodo=" + periodo + ", fechaPago=" + fechaPago
				+ ", payrollId=" + payrollId + ", orgId=" + orgId + ", curp="
				+ curp + ", categoria=" + categoria + ", locationId="
				+ locationId + ", timePeriodId=" + timePeriodId + ", idNomina="
				+ idNomina + ", departamento=" + departamento
				+ ", localidadPago=" + localidadPago + ", region=" + region
				+ ", registroPatronal=" + registroPatronal + ", claveRiesgo="
				+ claveRiesgo + ", antiguedad=" + antiguedad + ", ingreso="
				+ ingreso + ", contrato=" + contrato + ", banco=" + banco
				+ ", salario=" + salario + ", jornada=" + jornada
				+ ", lugarEmision=" + lugarEmision + ", email1=" + email1
				+ ", email2=" + email2 + ", getNumEmpleado()="
				+ getNumEmpleado() + ", getRfcEmpleado()=" + getRfcEmpleado()
				+ ", getNbArchivo()=" + getNbArchivo() + ", getProducto()="
				+ getProducto() + ", getSelloDigital()=" + getSelloDigital()
				+ ", getNoCertificadoCfd()=" + getNoCertificadoCfd()
				+ ", getUuid()=" + getUuid() + ", getFechaExpedicion()="
				+ getFechaExpedicion() + ", getFechaTimbrado()="
				+ getFechaTimbrado() + ", getNoCertificadoSat()="
				+ getNoCertificadoSat() + ", getSelloSat()=" + getSelloSat()
				+ ", getCadenaOriginal()=" + getCadenaOriginal()
				+ ", getCodigoBarras()=" + getCodigoBarras()
				+ ", getRfcEmpresa()=" + getRfcEmpresa()
				+ ", getRazonSocialEmpresa()=" + getRazonSocialEmpresa()
				+ ", getRegimenFiscalEmpresa()=" + getRegimenFiscalEmpresa()
				+ ", getTipoRegimenEmpresa()=" + getTipoRegimenEmpresa()
				+ ", getDomicilioFiscalEmpresa()="
				+ getDomicilioFiscalEmpresa() + ", getNbEmpleado()="
				+ getNbEmpleado() + ", getImss()=" + getImss()
				+ ", getPuesto()=" + getPuesto() + ", getDiaPago()="
				+ getDiaPago() + ", getMesPago()=" + getMesPago()
				+ ", getAnnioPago()=" + getAnnioPago() + ", getQuincena()="
				+ getQuincena() + ", getFechaInicialPago()="
				+ getFechaInicialPago() + ", getFechaFinalPago()="
				+ getFechaFinalPago() + ", getPeriodo()=" + getPeriodo()
				+ ", getFechaPago()=" + getFechaPago() + ", getPayrollId()="
				+ getPayrollId() + ", getOrgId()=" + getOrgId()
				+ ", getCurp()=" + getCurp() + ", getCategoria()="
				+ getCategoria() + ", getLocationId()=" + getLocationId()
				+ ", getTimePeriodId()=" + getTimePeriodId()
				+ ", getIdNomina()=" + getIdNomina() + ", getDepartamento()="
				+ getDepartamento() + ", getLocalidadPago()="
				+ getLocalidadPago() + ", getRegion()=" + getRegion()
				+ ", getRegistroPatronal()=" + getRegistroPatronal()
				+ ", getClaveRiesgo()=" + getClaveRiesgo()
				+ ", getAntiguedad()=" + getAntiguedad() + ", getIngreso()="
				+ getIngreso() + ", getContrato()=" + getContrato()
				+ ", getBanco()=" + getBanco() + ", getSalario()="
				+ getSalario() + ", getJornada()=" + getJornada()
				+ ", getLugarEmision()=" + getLugarEmision() + ", getEmail1()="
				+ getEmail1() + ", getEmail2()=" + getEmail2()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
