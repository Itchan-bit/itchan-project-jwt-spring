package com.portfolio.sample_project.security;

import org.springframework.stereotype.Component;

import com.portfolio.sample_project.model.JWTUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTValidator {

	private String secret = "youtube";

	public JWTUser validate(String token) {
		
		JWTUser jwtUser = null;
		
		try {
			
			Claims body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
			
			jwtUser = new JWTUser();
			
			jwtUser.setUsername(body.getSubject());
			jwtUser.setId(Long.parseLong((String)body.get("userId")));
			jwtUser.setRole((String) body.get("role"));
			
		} catch (Exception e) {
			System.out.println(e);
		}
		

		
		return jwtUser;
	}

}
