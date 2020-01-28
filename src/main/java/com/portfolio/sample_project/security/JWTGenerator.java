package com.portfolio.sample_project.security;

import org.springframework.stereotype.Component;

import com.portfolio.sample_project.model.JWTUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JWTGenerator {

	public String generate(JWTUser jwtuser) {
		
		Claims claims = Jwts.claims()
				.setSubject(jwtuser.getUsername());
		claims.put("userId", String.valueOf(jwtuser.getId()));
		claims.put("role", jwtuser.getRole());
		
		return Jwts.builder().setClaims(claims)
			.signWith(SignatureAlgorithm.HS512, "youtube")
			.compact();
			
		
	}

}
