package com.survey.springboot.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource datasource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication().dataSource(datasource)
    	  .usersByUsernameQuery(
    	   "select username,password,1 from users where username=?")
    	  .authoritiesByUsernameQuery(
    	   "select username, role from users where username=?");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/**/user/register","/welcome").permitAll()
        .antMatchers("/**/admin/**","/**/user/**").hasAuthority("ADMIN")
        .antMatchers("/**/vote/**").hasAnyAuthority("ADMIN","NORMAL")
        .anyRequest().authenticated();
        http.httpBasic();
        http.csrf().disable();
    }
    
}
