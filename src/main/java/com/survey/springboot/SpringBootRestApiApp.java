package com.survey.springboot;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.survey.springboot.service.UserService;


@SpringBootApplication(scanBasePackages={"com.*"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class SpringBootRestApiApp {
	

	@Value("${spring.datasource.driverClassName}")
	private String databaseDriverClassName;
	 
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	 
	@Value("${spring.datasource.username}")
	private String databaseUsername;
	 
	@Value("${spring.datasource.password}")
	private String databasePassword;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootRestApiApp.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
	
	@Bean
	public DataSource datasource() {
	    org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
	    ds.setDriverClassName(databaseDriverClassName);
	    ds.setUrl(datasourceUrl);
	    ds.setUsername(databaseUsername);
	    ds.setPassword(databasePassword);
	 
	    return ds;
	}
	
	
	@Bean
	public CommandLineRunner demo(UserService userService) {
		return (args) -> {
		};
	}

}
