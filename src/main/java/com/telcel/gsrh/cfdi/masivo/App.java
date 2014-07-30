package com.telcel.gsrh.cfdi.masivo;

import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.telcel.gsrh.cfdi.masivo.config.Configuracion;
import com.telcel.gsrh.cfdi.masivo.rmi.impl.RmiServiceImpl;

public class App {
	@SuppressWarnings("all")
	public static void main( String[] args ) throws InterruptedException {
    	ApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class);
    	RmiServiceImpl rmiServiceImpl = context.getBean("rmiServiceImpl", RmiServiceImpl.class);
    	ThreadPoolTaskExecutor taskExecutor = context.getBean("threadPoolTaskExecutor", ThreadPoolTaskExecutor.class);
    	rmiServiceImpl.setContext(context);
    	rmiServiceImpl.setTaskExecutor(taskExecutor);
    }
}
