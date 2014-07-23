package com.telcel.gsrh.cfdi.masivo;

import java.util.List;

import org.junit.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.telcel.gsrh.cfdi.masivo.config.Configuracion;
import com.telcel.gsrh.cfdi.masivo.domain.Archivo;
import com.telcel.gsrh.cfdi.masivo.domain.Credencial;
import com.telcel.gsrh.cfdi.masivo.domain.Empleado;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudUsuario;
import com.telcel.gsrh.cfdi.masivo.service.ReciboService;

public class AppTest {
	private ReciboService reciboService;
	private ApplicationContext context;
	private SolicitudUsuario solicitudUsuario;
	private static Logger logger = Logger.getLogger(AppTest.class);
	
	@Before
	public void setUp() {
		context = new AnnotationConfigApplicationContext(Configuracion.class);
		
		//Obtención de beans
		solicitudUsuario = context.getBean("solicitudUsuario", SolicitudUsuario.class);
		reciboService = context.getBean("reciboService", ReciboService.class);
	}
	
	@Test
	public void testEmpleadoMapper() {
		List<Empleado> empleados = reciboService.getEmpleadosTimbradoLocalidad(solicitudUsuario);
		Assert.assertNotNull(empleados);
		
		logger.info("testEmpleadoMapper() -> ok");
	}
	
	@Test
	public void testReciboArchivoMapper() {
		List<Archivo> archivos = reciboService.getArchivosTimbradoLocalidad(solicitudUsuario);
		Assert.assertNotNull(archivos);
		
		for(Archivo archivo : archivos) {
			logger.info(archivo.toString());
		}
		
		logger.info("testReciboArchivoMapper() -> ok");
	}
	
	@Test
	public void testCredencialMapper() {
		Credencial credencial = reciboService.getCredencialAppServerColdView();
		Assert.assertNotNull(credencial);
		logger.info(credencial);
		
		logger.info("testCredencialMapper() -> ok");
	}
}
