package com.dhk.utils;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
@Service
public class JwtUtils {
	
	private static final Logger logger= (Logger) LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

    private static final long serialVersionUID = 1L;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;
	
	/**
	 * tạo jwt từ username, date, expiration và secret
	 * */
	@SuppressWarnings("deprecation")
	public String generateJwtToken(Authentication authentication) 
	{
		AccountDetails accountPrincipal = (AccountDetails) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(accountPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date( (new Date()).getTime() + jwtExpirationMs ) )	
				.signWith(SignatureAlgorithm.HS512,jwtSecret)
				.compact();
		
	}
	// Lấy user name từ jwt
	public String getUserNameFromJwtToken(String token) {
		
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	
	// xác nhận jwt
	public boolean validateJwtToken(String authToken) {
		try {
		Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
		return true;
		}catch(SignatureException e) {
			logger.error("Invalid Jwt signature: {}",  e.getMessage());
		}catch(MalformedJwtException e) {
			logger.error("Invalid Jwt token: {}", e.getMessage());
		}catch(ExpiredJwtException e) {
			logger.error(" Jwt token expired: {}",  e.getMessage());
		}catch(UnsupportedJwtException e) {
			logger.error("Jwt token unsupported: {}", e.getMessage());
		}catch(IllegalArgumentException e) {
			logger.error("Jwt claims string is empty: {}", e.getMessage());
		}
		
		return false;
	}
}
