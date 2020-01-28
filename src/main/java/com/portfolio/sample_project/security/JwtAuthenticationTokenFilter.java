package com.portfolio.sample_project.security;

import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.portfolio.sample_project.model.JWTAuthenticationToken;


public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{

	public JwtAuthenticationTokenFilter() {
		super("/rest/**");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
	
		String header =  request.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Token ")) {
			throw new RuntimeException("JWT Token is Missing");
			
		}
		
		String authenticationToken = header.substring(6);
		
		JWTAuthenticationToken token = new JWTAuthenticationToken(authenticationToken);
		
		
		
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		super.successfulAuthentication(request, response, chain, authResult);
		
		chain.doFilter(request, response);
	}


	
}
