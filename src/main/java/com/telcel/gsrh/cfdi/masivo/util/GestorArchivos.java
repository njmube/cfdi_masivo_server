package com.telcel.gsrh.cfdi.masivo.util;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Credencial;
import com.telcel.gsrh.cfdi.masivo.service.CredencialService;

public class GestorArchivos {
	private GestorSftp gestorSftp;
	private Environment env;
	private Utils utils;
	
	@Autowired
	private CredencialService credencialService;
	
	private static Logger logger = Logger.getLogger(GestorArchivos.class);
	
	public void transferirArchivos(List<Archivo> archivos) {
		String nbArchivoRemotoZip = "";
		String rutaArchivosZip = env.getProperty("recibo.ruta.archivos");
		StringBuilder patZipFile = new StringBuilder();
		Credencial credencial = null;
		
		try {
			credencial = credencialService.getCredencialAppServerColdView();
			logger.info("credenciales de app server");
			
			if(archivos != null) {
				for(Archivo archivo: archivos) {
					nbArchivoRemotoZip = archivo.getNbArchivo().replace(".txt", "_spool.txt.zip");
						
					patZipFile.delete(0, patZipFile.length());
					patZipFile.append(rutaArchivosZip);
					patZipFile.append(File.separator);
					patZipFile.append(nbArchivoRemotoZip);
					
					logger.info("obteniendo archivo " + nbArchivoRemotoZip);
					gestorSftp.obtenerArchivoCfdiZipeado(patZipFile.toString(), nbArchivoRemotoZip, credencial);
					logger.info("archivo " + nbArchivoRemotoZip + " transferido");
				}
				
				logger.info("--------------------------");
				logger.info("Transferencia de archivos exitosa");
				
				utils.descomprimirArchivo(rutaArchivosZip);
				
				logger.info("getArchivos() -> OK");
			}
		} catch(Exception e) {
			logger.error("-- ERROR AL TRANSFERIR LOS ARCHIVOS --");
			logger.error(e.toString());
		}
	}

	public void setGestorSftp(GestorSftp gestorSftp) {
		this.gestorSftp = gestorSftp;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}
}
