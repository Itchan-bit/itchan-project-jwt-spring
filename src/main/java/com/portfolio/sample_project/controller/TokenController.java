package com.portfolio.sample_project.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.sample_project.model.JWTUser;
import com.portfolio.sample_project.security.JWTGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

	private JWTGenerator jwtGenerator;
	
	public TokenController(JWTGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}
	
	@PostMapping
	public String generate(@RequestBody final JWTUser username) {
		
		return jwtGenerator.generate(username);
		
		
	}
	
}
