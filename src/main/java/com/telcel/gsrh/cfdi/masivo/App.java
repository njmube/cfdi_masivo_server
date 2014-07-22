package com.telcel.gsrh.cfdi.masivo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.telcel.gsrh.cfdi.masivo.config.Configuracion;


public class App {
	@SuppressWarnings("all")
	public static void main( String[] args ) throws InterruptedException {
    	ApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
    }
}
