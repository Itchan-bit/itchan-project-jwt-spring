package com.portfolio.sample_project.model;




import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken {

	public String token;
	
	public JWTAuthenticationToken(Object principal, Object credentials) {
		super(null, null);
		
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
