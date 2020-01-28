package com.portfolio.sample_project.security;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.portfolio.sample_project.model.JWTAuthenticationToken;
import com.portfolio.sample_project.model.JWTUser;
import com.portfolio.sample_project.model.JWTUserDetails;

@Component
public class JWTAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	
	@Autowired
	private JWTValidator validator;
	
	@Override
	public boolean supports(Class<?> authentication) {
		return JWTAuthenticationToken.class.isAssignableFrom(authentication);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordToken)
			throws AuthenticationException {
		
		JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) usernamePasswordToken;
		
		String token = jwtAuthenticationToken.getToken();
		
		 JWTUser jwtUser = validator.validate(token);
		 
		 if(jwtUser == null) {
			 	throw new RuntimeException("JWT TOKEN IS INCORRECT");
		 }
		
		 List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				 .commaSeparatedStringToAuthorityList(jwtUser.getRole());
		 
		 
		 new JWTUserDetails	(jwtUser.getUsername() , jwtUser.getId(), 
				 token,
				 grantedAuthorities);
		return null;
	}

	
}
