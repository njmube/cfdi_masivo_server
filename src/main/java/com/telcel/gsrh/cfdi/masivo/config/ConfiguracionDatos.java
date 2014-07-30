package com.telcel.gsrh.cfdi.masivo.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("com.telcel.gsrh.cfdi.masivo.mapper")
public class ConfiguracionDatos {

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@10.191.148.29:1524:TIMB");//Producción
		//ds.setUrl("jdbc:oracle:thin:@10.191.148.71:1524:TIMB");//Test
		ds.setUsername("coldview");
		ds.setPassword("coldview");
		
		return ds;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setTypeAliasesPackage("com.telcel.gsrh.cfdi.masivo.domain");
		
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
}
