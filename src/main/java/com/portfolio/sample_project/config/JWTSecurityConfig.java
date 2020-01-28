package com.portfolio.sample_project.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.portfolio.sample_project.security.JWTAuthenticationEntryPoint;
import com.portfolio.sample_project.security.JWTAuthenticationProvider;
import com.portfolio.sample_project.security.JwtSuccessHandler;
import com.portfolio.sample_project.securty.JwtAuthenticationTokenFilter;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private JWTAuthenticationProvider authenticationProvider;
	private JWTAuthenticationEntryPoint entryPoint;
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
	
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
			.authorizeRequests().antMatchers("**/rest/**").authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(entryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationToken.class);
		
		http.headers().cacheControl();
	}
	
	
	
}
