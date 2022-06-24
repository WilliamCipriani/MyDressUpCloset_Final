package com.example;

import com.example.serviceimpl.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.auth.handler.LoginSucessHandler;

@SuppressWarnings("deprecation")
@EnableGlobalMethodSecurity(securedEnabled = true)

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JpaUserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private LoginSucessHandler sucessHandler;

	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests()
			.antMatchers("/user/**").permitAll()
			.antMatchers("/home/**").permitAll()
			.antMatchers("/myProducts/**").permitAll()
			.antMatchers("/product/**").permitAll()
			.antMatchers("/purchase/**").permitAll()
			.antMatchers("/admiHome/**").access("hasRole('ROLE_ADMIN')")
			.and().formLogin().loginPage("/login")
			.loginProcessingUrl("/login").permitAll()
			.successHandler(sucessHandler)
			.and().logout().logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll()
			.and().exceptionHandling().accessDeniedPage("/error_403");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}