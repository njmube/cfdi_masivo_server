package com.telcel.gsrh.cfdi.masivo.recibo.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.DatosPago;
import com.telcel.gsrh.cfdi.masivo.domain.DetalleRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudNominaEmpleado;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboEnvio;
import com.telcel.gsrh.cfdi.masivo.service.ReciboService;
import com.telcel.gsrh.cfdi.masivo.util.Utils;

public class ReciboEnvioPdf implements ReciboEnvio {
	@Autowired
	private ReciboService reciboService;
	
	private Utils utils;
	private Empleado empleado;
	private SolicitudNominaEmpleado solicitudNominaEmpleado;
	private static Logger logger = Logger.getLogger(ReciboEnvioPdf.class);
	
	public void generarRecibo() throws Exception {
		logger.info("empleado: " + empleado.getNumEmpleado());
		
		solicitudNominaEmpleado.setAnnio(empleado.getAnnioPago());
		solicitudNominaEmpleado.setNumEmpleado(empleado.getNumEmpleado());
		solicitudNominaEmpleado.setPeriodo(empleado.getQuincena());
		solicitudNominaEmpleado.setRfcEmpresa(empleado.getRfcEmpresa());
		solicitudNominaEmpleado.setTpRecibo(utils.getTpNomina(empleado.getProducto()));
		
		//Obtiene percepciones
		List<DetalleRecibo> percepciones = reciboService.getDetallePercepciones(solicitudNominaEmpleado);
		
		//Obtiene deducciones
		List<DetalleRecibo> deducciones = reciboService.getDetalleDeducciones(solicitudNominaEmpleado);
		
		//Concentra percepciones y deducciones
		List<DetalleRecibo> detalleRecibo = utils.getDetalleRecibo(percepciones, deducciones);
		
		//Obtiene datos de pago 
		DatosPago datosPago = reciboService.getDatosPago(solicitudNominaEmpleado);
		
		crearPdf(detalleRecibo, datosPago);
	}
	
	private void crearPdf(List<DetalleRecibo> detalleRecibo, DatosPago datosPago) throws Exception {
		JasperPrint jasperPrint = null;
		JRDataSource dataSourceJasper = null;
		Map<String, Object> params = new HashMap<String, Object>();
		String rutaJasper = utils.getRutaPlantillaJasper();
		String rutaImagenLogo = utils.getRutaImagenLogo();
		String rutaImagenQrCodigo = utils.getRutaImagenQrCodigo(empleado);
		String rutaReciboPdf = utils.getRutaArchivo(empleado, "pdf");
		
		params.put("rutaImagenLogo", rutaImagenLogo);
		params.put("razonSocialEmisor", empleado.getRazonSocialEmpresa());
		params.put("rfcEmisor", empleado.getRfcEmpresa());
		params.put("domicilioFiscal", empleado.getDomicilioFiscalEmpresa());
		params.put("noEmpleado", empleado.getNumEmpleado());
		params.put("nbEmpleado", empleado.getNbEmpleado());
		params.put("rfcEmpleado", empleado.getRfcEmpleado());
		params.put("localidadEmpleado", empleado.getLocalidadPago());
		params.put("region", empleado.getRegion());
		params.put("curpEmpleado", empleado.getCurp());
		
		params.put("registroPatronal", empleado.getRegistroPatronal());
		params.put("tipoRegimen", empleado.getTipoRegimenEmpresa());
		params.put("claveRiesgo", String.valueOf(empleado.getClaveRiesgo()));
		
		params.put("imssEmpleado", empleado.getImss());
		params.put("deptoEmpleado", empleado.getDepartamento());
		params.put("puestoEmpleado", empleado.getPuesto());
		params.put("categoriaEmpleado", empleado.getCategoria());
		params.put("antiguedad", String.valueOf(empleado.getAntiguedad()));
		params.put("fechaInicioRelacionLaboral", empleado.getIngreso());
		params.put("tipoJornada", empleado.getJornada());
		params.put("tipoContratacion", empleado.getContrato());
		
		params.put("fechaPago", empleado.getFechaPago());
		params.put("periodo", empleado.getPeriodo());
		params.put("quincena", empleado.getQuincena());
		params.put("diasPagados", datosPago.getDiasPagados());
		params.put("banco", empleado.getBanco());
		params.put("periodicidadPago", datosPago.getPeriodicidad());
		params.put("sueldoBase", empleado.getSalario());
		params.put("sdiEmpleado", datosPago.getSdi());
		
		params.put("gravados", datosPago.getGravado());
		params.put("exentos", datosPago.getExento());
		
		params.put("fechaEmision", empleado.getFechaExpedicion());
		params.put("lugarEmision", empleado.getLugarEmision());
		params.put("noSerieCSD", empleado.getNoCertificadoCfd());
		params.put("regimenFiscal", empleado.getRegimenFiscalEmpresa());
		params.put("folioFiscal", empleado.getUuid());
		params.put("noSerieCertificadoSAT", empleado.getNoCertificadoSat());
		params.put("fechaCertificacion", empleado.getFechaTimbrado());
		
		params.put("cadenaOriginalComplemento", empleado.getCadenaOriginal());
		params.put("selloDigitalCDFI", empleado.getSelloDigital());
		params.put("selloSAT", empleado.getSelloSat());
		
		params.put("acuse", datosPago.getLeyenda());
	
		utils.generarCodigoQr(empleado.getCodigoBarras(), rutaImagenQrCodigo);
		params.put("rutaImagenQrCode", rutaImagenQrCodigo.toString());
		
		dataSourceJasper = crearReportDataSource(detalleRecibo);
		
		jasperPrint = JasperFillManager.fillReport (rutaJasper, params, dataSourceJasper);
		
		popularArchivoPdf(rutaReciboPdf, jasperPrint);
	}
	
	private JRDataSource crearReportDataSource(List<DetalleRecibo> lstDetalle) {
		Collection<DetalleRecibo> coleccion = (Collection<DetalleRecibo>)lstDetalle;
		
		return new JRBeanCollectionDataSource(coleccion);
	}
	
	public void popularArchivoPdf(String rutaArchivo, JasperPrint jasperPrint) throws Exception {
		File archivoLocal = new File(rutaArchivo);
		OutputStream salida = new FileOutputStream(archivoLocal);
		
		JRExporter exporter = new JRPdfExporter(); 
    	exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint); 
    	exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, salida); 
    	exporter.exportReport();
		
		salida.flush();
		salida.close();
		
		salida = null;
		System.gc();
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public void setSolicitudNominaEmpleado(SolicitudNominaEmpleado solicitudNominaEmpleado) {
		this.solicitudNominaEmpleado = solicitudNominaEmpleado;
	}

	public void generarRecibo(Archivo archivo, SolicitudEnvioRecibo solicitud)throws Exception {
		
	}
}
