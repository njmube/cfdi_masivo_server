package com.telcel.gsrh.cfdi.masivo.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Scope;
import org.springframework.remoting.rmi.RmiServiceExporter;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.telcel.gsrh.cfdi.masivo.action.EnvioReciboAction;
import com.telcel.gsrh.cfdi.masivo.action.LocalidadReciboAction;
import com.telcel.gsrh.cfdi.masivo.domain.SolicitudNominaEmpleado;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboEnvio;
import com.telcel.gsrh.cfdi.masivo.recibo.ReciboLocalidad;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboEnvioPdf;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboEnvioXml;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboLocalidadPdf;
import com.telcel.gsrh.cfdi.masivo.recibo.impl.ReciboLocalidadXml;
import com.telcel.gsrh.cfdi.masivo.rmi.RmiService;
import com.telcel.gsrh.cfdi.masivo.rmi.impl.RmiServiceImpl;
import com.telcel.gsrh.cfdi.masivo.util.GestorSftp;
import com.telcel.gsrh.cfdi.masivo.util.GestorArchivos;
import com.telcel.gsrh.cfdi.masivo.util.Utils;

@Configuration
@PropertySource("classpath:mensajes_es_MX.properties")
@Import(ConfiguracionDatos.class)
@ComponentScan(basePackages= {
		"com.telcel.gsrh.cfdi.masivo.service.impl",
		"com.telcel.gsrh.cfdi.masivo.recibo.impl"
})
public class Configuracion {
	@Autowired
	private Environment env;
	
	@Bean(name="solicitudNominaEmpleado")
	public SolicitudNominaEmpleado solicitudNominaEmpleado() {
		return new SolicitudNominaEmpleado();
	}
	
	@Bean(name="utils")
	public Utils utils() {
		Utils utils = new Utils();
		utils.setEnv(env);
		
		return utils; 
	}
	
	@Bean(name="gestorSftp")
	public GestorSftp gestorSftp() {
		return new GestorSftp(); 
	}
	
	@Bean(name="gestorArchivos")
	public GestorArchivos gestorArchivos() {
		GestorArchivos gestorArchivos = new GestorArchivos();
		gestorArchivos.setEnv(env);
		gestorArchivos.setUtils(utils());
		gestorArchivos.setGestorSftp(gestorSftp());
		
		return gestorArchivos;
	}
	
	@Bean(name="reciboLocalidadPdf")
	public ReciboLocalidad reciboLocalidadPdf() {
		ReciboLocalidadPdf reciboLocalidadPdf = new ReciboLocalidadPdf();
		reciboLocalidadPdf.setUtils(utils());
		reciboLocalidadPdf.setSolicitudNominaEmpleado(solicitudNominaEmpleado());
		
		return reciboLocalidadPdf;
	}
	
	@Bean(name="reciboLocalidadXml")
	public ReciboLocalidad reciboLocalidadXml() {
		ReciboLocalidadXml reciboLocalidadXml = new ReciboLocalidadXml();
		reciboLocalidadXml.setUtils(utils());
		
		return reciboLocalidadXml;
	}
	
	@Bean(name="reciboEnvioPdf")
	public ReciboEnvio reciboEnvioPdf() {
		ReciboEnvio reciboEnvioPdf = new ReciboEnvioPdf();
		reciboEnvioPdf.setUtils(utils());
		reciboEnvioPdf.setSolicitudNominaEmpleado(solicitudNominaEmpleado());
		
		return reciboEnvioPdf;
	}
	
	@Bean(name="reciboEnvioXml")
	public ReciboEnvio reciboEnvioXml() {
		ReciboEnvio reciboEnvioXml = new ReciboEnvioXml();
		reciboEnvioXml.setUtils(utils());
		reciboEnvioXml.setSolicitudNominaEmpleado(solicitudNominaEmpleado());
		
		return reciboEnvioXml;
	}
	
	@Bean(name="localidadReciboAction")
	@Scope("prototype")
	public LocalidadReciboAction reciboAction() {
		LocalidadReciboAction reciboAction = new LocalidadReciboAction();
		reciboAction.setGestorArchivos(gestorArchivos());
		reciboAction.setUtils(utils());
		
		return reciboAction;
	}
	
	@Bean(name="envioReciboAction")
	@Scope("prototype")
	public EnvioReciboAction envioReciboAction() {
		EnvioReciboAction envioReciboAction = new EnvioReciboAction();
		envioReciboAction.setGestorArchivos(gestorArchivos());
		
		return envioReciboAction;
	}
	
	@Bean(name="securityManager")
	public SecurityManager securityManager() {
		SecurityManager securityManager = new SecurityManager();
		
		return securityManager;
	}
	
	@Bean
	public RmiServiceImpl rmiService() {
		return new RmiServiceImpl();
	}
	
	@Bean
	public RmiServiceExporter rmiExporter() {
		RmiServiceExporter rmiExporter = new RmiServiceExporter();
		rmiExporter.setServiceName("reciboService");
		rmiExporter.setService(rmiService());
		rmiExporter.setServiceInterface(RmiService.class);
		rmiExporter.setRegistryPort(1099);
		rmiExporter.setRegisterTraceInterceptor(true);
		
		return rmiExporter;
	}
	
	@Bean(name="threadPoolTaskExecutor")
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(1);
		threadPoolTaskExecutor.setMaxPoolSize(20);
		threadPoolTaskExecutor.setQueueCapacity(15);
		
		return threadPoolTaskExecutor;
	}
}
