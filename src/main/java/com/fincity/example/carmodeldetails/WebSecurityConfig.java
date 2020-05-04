package com.fincity.example.carmodeldetails;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Create 2 users for demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user@gmail.com").password("{noop}password").roles("USER").and()
				.withUser("admin@gmail.com").password("{noop}password").roles("USER", "ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

				// HTTP Basic authentication
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/car/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/car").hasRole("ADMIN").antMatchers(HttpMethod.PUT, "/car/**")
				.hasRole("ADMIN").antMatchers(HttpMethod.PATCH, "/car/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/car/**").hasRole("ADMIN").and().csrf().disable().formLogin()
				.disable();
	}

}