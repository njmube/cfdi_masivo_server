package com.telcel.gsrh.cfdi.masivo.util;

import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.springframework.core.env.Environment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.DetalleRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudEnvioRecibo;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudNominaEmpleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;

@SuppressWarnings("all")
public class Utils {
	private Environment env;
	
	public void descomprimirArchivo(String rutaArchivosZip) throws Exception {
		StringBuilder sbPath = new StringBuilder();
		File dirZip = new File(rutaArchivosZip);
		File aux = null;
	    String[] lstArchivos = dirZip.list();
		
		if(lstArchivos != null) {
	    	for(String nbArchivoZip : lstArchivos) {
				aux = new File(rutaArchivosZip + File.separator + nbArchivoZip);
				
				if(aux.isFile()) {
					InputStream fis = new FileInputStream(rutaArchivosZip + File.separator + nbArchivoZip);
					BufferedInputStream bfStream = new BufferedInputStream(fis);
					ZipInputStream zis = new ZipInputStream(bfStream);
					ZipEntry entry = null; 
					
					if((entry = zis.getNextEntry()) != null) {
						sbPath.delete(0, sbPath.length());
						sbPath.append(rutaArchivosZip);
						sbPath.append(entry.getName().replace("_spool.txt", ".txt"));
						
						File archivoLocal = new File(sbPath.toString());
						OutputStream salida = null;
						
						//archivoLocal.setWritable(true);
						salida = new FileOutputStream(archivoLocal);
						
						System.out.println("Extrayendo: " + sbPath.toString());
						
						IOUtils.copy(zis, salida);
						
						salida.flush();
						salida.close();
						salida = null;
					}
				
					fis.close();
					bfStream.close();
					zis.close();
					
					fis = null;
					bfStream = null;
					
					zis = null;
					System.gc();
				}
			}
		}
	}
	
	public int getTpNomina(String nbNomina) {
		if(nbNomina.equals("VALES"))
			return 244;
		else if(nbNomina.equals("NOMINA"))
			return 245;
		else if(nbNomina.equals("FAHORRO"))
			return 261;
		else if(nbNomina.equals("UTILES"))
			return 262;
		else if(nbNomina.equals("AGUINALDO"))
			return 263;
		else if(nbNomina.equals("PTU"))
			return 382;
		else
			return -1;
	}
	
	public List<DetalleRecibo> getDetalleRecibo(List<DetalleRecibo> percepciones, List<DetalleRecibo> deducciones) {
		List<DetalleRecibo> detalleRecibo = new ArrayList<DetalleRecibo>();
		
		if(percepciones != null) {
			for(DetalleRecibo percepcion : percepciones) {
				detalleRecibo.add(percepcion);
			}
		}
		
		if(deducciones != null) {
			for(DetalleRecibo deduccion : deducciones) {
				detalleRecibo.add(deduccion);
			}
		}
		
		return detalleRecibo;
	}
	
	public String getRutaImagenQrCodigo(Empleado empleado) {
		StringBuilder rutaImagenQrCodigo = new StringBuilder();
		rutaImagenQrCodigo.append(env.getProperty("recibo.ruta.imagenes"));
		rutaImagenQrCodigo.append(empleado.getProducto());
		rutaImagenQrCodigo.append("_");
		rutaImagenQrCodigo.append(empleado.getQuincena());
		rutaImagenQrCodigo.append("_");
		rutaImagenQrCodigo.append(empleado.getAnnioPago());
		rutaImagenQrCodigo.append("_");
		rutaImagenQrCodigo.append(empleado.getNumEmpleado());
        rutaImagenQrCodigo.append(".jpg");
		
		return rutaImagenQrCodigo.toString();
	}
	
	public String getRutaArchivo(Empleado empleado, String formato) {
		StringBuilder nbArchivoPdf = new StringBuilder();
		nbArchivoPdf.append(env.getProperty("recibo.ruta." + formato));
		nbArchivoPdf.append(empleado.getProducto());
		nbArchivoPdf.append("_");
		nbArchivoPdf.append(empleado.getQuincena());
		nbArchivoPdf.append("_");
		nbArchivoPdf.append(empleado.getAnnioPago());
		nbArchivoPdf.append("_");
		nbArchivoPdf.append(empleado.getNumEmpleado());
        nbArchivoPdf.append(".").append(formato);
		
		return nbArchivoPdf.toString();
	}
	
	public String getRutaPlantillaJasper() {
		return env.getProperty("recibo.ruta.jasper") + "ReciboNominaTelcel.jasper";
	}
	
	public String getRutaImagenLogo() {
		return env.getProperty("recibo.ruta.jasper") + "logoheader.png";
	}

	public void setEnv(Environment env) {
		this.env = env;
	}
	
	public void generarCodigoQr(String codigoBarras, String ruta) throws Exception {
		ByteArrayOutputStream out = QRCode.from(codigoBarras).withSize(200, 200).to(ImageType.PNG).stream();
		
		FileOutputStream fout = new FileOutputStream(new File(ruta));
		fout.write(out.toByteArray());
		
		fout.flush();
		fout.close();
		
		out.close();
		
		fout = null;
		out = null;
		System.gc();
	}
	
	public List<String> comprimirProductos(Set<String> productosLocalidad, SolicitudUsuario solicitud) throws Exception {
		List<String> archivosZip = new ArrayList<String>();
		
		for(String producto : productosLocalidad) {
			String archivoZip = asociarProductoEmpleado(solicitud, String.valueOf(producto));
			archivosZip.add(archivoZip);
		}
		
		eliminarArchivosGenerados();
		
		return archivosZip;
	}
	
	private String asociarProductoEmpleado(SolicitudUsuario solicitud, String productoEjecucion) throws Exception {
		String nbArchivoZipXml = getNomenclaturaZip(solicitud.getPeriodo(), solicitud.getAnnio(), productoEjecucion, "xml");
		String nbArchivoZipPdf = getNomenclaturaZip(solicitud.getPeriodo(), solicitud.getAnnio(), productoEjecucion, "pdf");
		String rutaArchivosZip = env.getProperty("recibo.ruta.zip") + solicitud.getCveLocalidad() + File.separator;
		String archivoZip = "";
		
		if(solicitud.getFormato().equalsIgnoreCase("xml")) {
			if(validaCarpeta(rutaArchivosZip)) {
				archivoZip = zipearArchivos(env.getProperty("recibo.ruta.xml"), rutaArchivosZip, nbArchivoZipXml, getPatronNomenclatura(solicitud.getPeriodo(), solicitud.getAnnio(), productoEjecucion));
			}
		} else if(solicitud.getFormato().equalsIgnoreCase("pdf")) {
			if(validaCarpeta(rutaArchivosZip)) {
				archivoZip = zipearArchivos(env.getProperty("recibo.ruta.pdf"), rutaArchivosZip, nbArchivoZipPdf, getPatronNomenclatura(solicitud.getPeriodo(), solicitud.getAnnio(), productoEjecucion));
			}
		}
		
		return archivoZip;
	}
	
	public void eliminarArchivosGenerados() throws Exception {
		//Elimina los archivos generados
		eliminarArchivos(env.getProperty("recibo.ruta.xml"));
		eliminarArchivos(env.getProperty("recibo.ruta.pdf"));
		eliminarArchivos(env.getProperty("recibo.ruta.imagenes"));
		eliminarArchivos(env.getProperty("recibo.ruta.archivos"));
	}
	
	public void crearDirectorioLocalidadPdf(SolicitudUsuario solicitud) {
		StringBuilder directorio = new StringBuilder();
		directorio.append(env.getProperty("recibo.ruta.pdf"));
		directorio.append(solicitud.getCveLocalidad()).append(File.separator);
		directorio.append(solicitud.getAnnio()).append(File.separator);
		directorio.append(solicitud.getPeriodo()).append(File.separator);
		directorio.append(solicitud.getCveLocalidad()).append(File.separator);
		
		File archivoTmp = new File(directorio.toString());
		
		if(!archivoTmp.exists())
			archivoTmp.mkdir();
	}
	
	private boolean validaCarpeta(String ruta) throws Exception {
		File carpeta = new File(ruta);
		
		if(!carpeta.exists()) {
			if(!carpeta.mkdir())
				throw new Exception("Imposible crear el directorio " + ruta);
		}
		
		return carpeta.exists();
	}
	
	private String getNomenclaturaZip(int periodo, int annio, String producto, String tpArchivos) {
		StringBuilder archivoZip = new StringBuilder();
		archivoZip.append(producto);
		archivoZip.append("_");
		archivoZip.append(periodo);
		archivoZip.append("_");
		archivoZip.append(annio);
		archivoZip.append("_");
		archivoZip.append(tpArchivos);
		archivoZip.append(".zip");
		
		return archivoZip.toString();
	}
	
	private String getPatronNomenclatura(int periodo, int annio, String producto) {
		StringBuilder patron = new StringBuilder();
		patron.append(producto);
		patron.append("_");
		patron.append(periodo);
		patron.append("_");
		patron.append(annio);
		patron.append("_");
		
		return patron.toString();
	}
	
	private String zipearArchivos(String rutaArchivos, String rutaExport, String nbArchivoZip, String patron) throws Exception {
        String archivoEntrada = "";
        String nbArchZip = rutaExport + nbArchivoZip;
        
        String[] archivos = null;
        File archivo = null;
        
        validaCarpeta(rutaExport);
        
        FileOutputStream out = new FileOutputStream(nbArchZip);
        ZipOutputStream zipOut = new ZipOutputStream(out);
        
        zipOut.setLevel(0);
            
        archivos = getArchivos(rutaArchivos);
            
        if(archivos != null) {
            for(String nbArchivo : archivos) {
				if(nbArchivo.startsWith(patron)) {
					archivoEntrada = rutaArchivos + nbArchivo;
					archivo =  new File(archivoEntrada);
						
					FileInputStream in = new FileInputStream(archivo);
					
					byte b[] = new byte[2048];
						
					ZipEntry entry = new ZipEntry(archivo.getName());
					zipOut.putNextEntry(entry);
						
					int len = 0;
					while ((len = in.read(b)) != -1) {
						zipOut.write(b, 0, len);
					}
					
					in.close();
				}
            }
        }
		
		zipOut.closeEntry();
        zipOut.close();
		
		out.flush();
		out.close();
		
		out = null;
		zipOut = null;
		
		System.gc();
		
		return nbArchZip;
    }
	
	private String[] getArchivos(String rutaArchivos) {
        File rtaArchivos = new File(rutaArchivos);
        String[] archivos = null;
        
        if(rtaArchivos.exists()) {
            archivos = rtaArchivos.list();
        }
        
        return archivos;
    }
	
	private void eliminarArchivos(String rutaRaiz) throws Exception {
		StringBuilder sbPathArchivo = new StringBuilder();
		File dirRaiz = null;
		File archivoAux = null;
		
		dirRaiz = new File(rutaRaiz);
	    String[] lstArchivos = dirRaiz.list();
	    
	    if(lstArchivos != null) {
	    	for(String nbArchivo : lstArchivos) {
	    		sbPathArchivo = new StringBuilder();
		    	sbPathArchivo.append(rutaRaiz);
		    	sbPathArchivo.append(nbArchivo);
		    	
		    	archivoAux = new File(sbPathArchivo.toString());
		    	//archivoAux.setWritable(true);
		    	
				if(archivoAux.isFile()) {
					if(!archivoAux.delete()) 
						throw new Exception("Imposible eliminar el archivo " + rutaRaiz);
				}
	    	}
	    }
	}
	
	public List<String> getArchivosPeriodoAnnioLocalidadFormato(SolicitudUsuario solicitud) {
		List<String> archivos = new ArrayList<String>(); 
		String rutaArchivosZip = env.getProperty("recibo.ruta.zip") + solicitud.getCveLocalidad() + File.separator;
		File carpeta = new File(rutaArchivosZip);
		
		StringBuilder patron = new StringBuilder();
		patron.append("_").append(solicitud.getPeriodo());
		patron.append("_").append(solicitud.getAnnio());
		patron.append("_").append(solicitud.getFormato());
		patron.append(".zip");
		
		if(carpeta.exists()) {
			String[] archivosContenidos = carpeta.list();
			
			if(archivosContenidos != null) {
				for(String archivo : archivosContenidos) {
					if(archivo.endsWith(patron.toString())) {
						archivos.add(rutaArchivosZip + archivo);
					}
				}
			}
		}
		
		return archivos;
	}
	
	public File getArchivoSpool(String nbArchivoSpool) {
		StringBuilder rutaArchivoSpool = new StringBuilder();
		rutaArchivoSpool.append(env.getProperty("recibo.ruta.archivos"));
		rutaArchivoSpool.append(nbArchivoSpool);
		
		File archivoSpool = new File(rutaArchivoSpool.toString());
		
		return archivoSpool;
	}
	
	public String encontrarXmlEmpleado(File archivoSpool, Empleado empleado) throws Exception {
		InputStream inputStream = new FileInputStream(archivoSpool);
		InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF8");
		BufferedReader reader = new BufferedReader(streamReader);
		
		boolean encontradoInicio = false;
		boolean encontradoFin = false;
		String linea = "";
		StringBuilder xmlEmpleado = new StringBuilder();
		
		while ((linea = reader.readLine()) != null) {
			linea = linea.substring(70);
		    
			if(!encontradoInicio) {
				if(encontrarTagInicial(linea)) {//Encuentra inicio
			    	encontradoInicio = true;
			    	xmlEmpleado.delete(0, xmlEmpleado.length());
			    	xmlEmpleado.append(linea);
			    }
		    } else {//Si ya está encontrado el tag inicial
		    	if(encontrarTagFinal(linea))//Encuentra fin
		    		encontradoFin = true;
			    
		    	xmlEmpleado.append(linea);
		    }
		    
		    if(encontradoInicio && encontradoFin) {
		    	//Analiza que el rfc del empleado esté en el cfdi 
		    	if(encontrarAtributoRfc(xmlEmpleado.toString(), empleado.getRfcEmpleado()))//Si el rfc está en el cfdi correcto
		    		break;
		    	else {
					encontradoInicio = false;
					encontradoFin = false;
				}
		    	
		    }
		}
		
		archivoSpool = null;
		
		inputStream.close();
		inputStream = null;
		
		streamReader.close();
		streamReader = null;
		
		reader.close();
		reader = null;
		
		System.gc();
		
		return xmlEmpleado.toString();
	}
	
	public boolean encontrarTagInicial(String linea) {
		Pattern pattern = Pattern.compile("<?xml.*?>");
		Matcher matcher = pattern.matcher(linea);
		
		if (matcher.find())
			return true;
		
		return false;
	}
	
	public boolean encontrarTagFinal(String linea) {
		Pattern pattern = Pattern.compile("<?/cfdi:Comprobante.*?>");
		Matcher matcher = pattern.matcher(linea);
		
		if (matcher.find())
			return true;
		
		return false;
	}
	
	public boolean encontrarAtributoRfc(String cfdi, String rfcEmpleado) {
		String rfcAttributo = "rfc=\""+ rfcEmpleado + "\"";
		Pattern pattern = Pattern.compile(rfcAttributo);
		Matcher matcher = pattern.matcher(cfdi);
		
		if (matcher.find())
			return true;
		
		return false;
	}
	
	public void generarDocumentoXmlEmpleado(String xml, Empleado empleado, String formato) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder = factory.newDocumentBuilder();  
	    Document document = builder.parse(new InputSource(new StringReader(xml)));
		
	    document.normalize();
	    document.normalizeDocument();
	    
	    OutputFormat format = new OutputFormat(document);
	    format.setLineWidth(65);
		format.setIndenting(true);
		format.setIndent(2);
		
		String rutaArchivoXml = getRutaArchivo(empleado, formato);
		File archivoLocal = new File(rutaArchivoXml);
		OutputStream salida = new FileOutputStream(archivoLocal);
		
		XMLSerializer serializer = new XMLSerializer(salida, format);
		serializer.serialize(document);
		
		salida.flush();
		salida.close();
		
		salida = null;
		System.gc();
	}
	
	public void procesarArchivoSpool(Archivo archivo, SolicitudEnvioRecibo solicitud) throws Exception {
		File archivoSpool = getArchivoSpool(archivo.getNbArchivo());
		LineIterator it = FileUtils.lineIterator(archivoSpool, "UTF8");
		
		String linea = "";
		int contadorXmlEncontrados = 0;
		boolean encontradoInicio = false;
		boolean encontradoFin = false;
		StringBuilder xmlEmpleado = new StringBuilder();
		
		while(it.hasNext()) {
			linea = it.nextLine().substring(70);
			
			if(!encontradoInicio) {
				if(encontrarTagInicial(linea)) {//Encuentra inicio
			    	encontradoInicio = true;
			    	xmlEmpleado.append(linea);
			    }
		    } else {//Si ya está encontrado el tag inicial
		    	if(encontrarTagFinal(linea))//Encuentra fin
		    		encontradoFin = true;
			    
		    	xmlEmpleado.append(linea);
		    }
		    
		    if(encontradoInicio && encontradoFin) {
		    	contadorXmlEncontrados++;
				
				//Generar archivo xml correspondiente
				generarDocumentoXmlSpoolEmpleado(xmlEmpleado.toString(), archivo, solicitud);
				
				encontradoInicio = false;
				encontradoFin = false;
				xmlEmpleado.delete(0, xmlEmpleado.length());
			}
		}
		
		archivoSpool = null;
		
		System.gc();
	}
	
	public void generarDocumentoXmlSpoolEmpleado(String xml, Archivo archivo, SolicitudEnvioRecibo solicitud) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	    DocumentBuilder builder = factory.newDocumentBuilder();  
	    Document document = builder.parse(new InputSource(new StringReader(xml)));
		String numeroEmpleado = "";
		
	    document.normalize();
	    document.normalizeDocument();
	    
	    OutputFormat format = new OutputFormat(document);
	    format.setLineWidth(65);
		format.setIndenting(true);
		format.setIndent(2);
		
		numeroEmpleado = getNumeroEmpleadoFromXml(document);
		
		String rutaArchivoXml = getRutaArchivoXml(numeroEmpleado, archivo.getProducto(), solicitud.getPeriodo(), solicitud.getAnnio());
		File archivoLocal = new File(rutaArchivoXml);
		OutputStream salida = new FileOutputStream(archivoLocal);
		
		XMLSerializer serializer = new XMLSerializer(salida, format);
		serializer.serialize(document);
		
		salida.flush();
		salida.close();
		
		salida = null;
		System.gc();
	}
	
	public String getNumeroEmpleadoFromXml(Document document) {
		String numeroEmpleado = "";
		NodeList listaNodosNomina = document.getElementsByTagName("nomina:Nomina");
		
		for (int i = 0; i < listaNodosNomina.getLength(); i++) {
			Node nodoNomina = listaNodosNomina.item(i);
	 		if (nodoNomina.getNodeType() == Node.ELEMENT_NODE) {
	 
				Element elementoNomina = (Element) nodoNomina;
				numeroEmpleado = elementoNomina.getAttribute("NumEmpleado");
			}
		}
		
		return numeroEmpleado;
	}
	
	public String getRutaArchivoXml(String numeroEmpleado, String producto, int periodo, int annio) {
		StringBuilder sbPathServer = new StringBuilder();
		sbPathServer.append(env.getProperty("recibo.ruta.xml"));
		sbPathServer.append(producto);
		sbPathServer.append("_");
		sbPathServer.append(periodo);
		sbPathServer.append("_");
		sbPathServer.append(annio);
		sbPathServer.append("_");
		sbPathServer.append(numeroEmpleado);
        sbPathServer.append(".xml");
		
		return sbPathServer.toString();
	}
	
	public String getRutaArchivoPdf(String numeroEmpleado, String producto, int periodo, int annio) {
		StringBuilder sbPathServer = new StringBuilder();
		sbPathServer.append(env.getProperty("recibo.ruta.pdf"));
		sbPathServer.append(producto);
		sbPathServer.append("_");
		sbPathServer.append(periodo);
		sbPathServer.append("_");
		sbPathServer.append(annio);
		sbPathServer.append("_");
		sbPathServer.append(numeroEmpleado);
        sbPathServer.append(".pdf");
		
		return sbPathServer.toString();
	}
	
	public String getValueProperties(String key) {
		return env.getProperty(key);
	}
	
	public boolean existeArchivo(String ruta) {
		File archivo = new File(ruta);
		
		if(archivo.exists())
			return true;
		
		return false;
	}
}
