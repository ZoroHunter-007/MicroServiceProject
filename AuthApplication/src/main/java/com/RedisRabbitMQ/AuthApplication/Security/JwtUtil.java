package com.RedisRabbitMQ.AuthApplication.Security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET_KEY="mysecretkeymysecretkeymysecretkey12345";
	
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+360000))
				.signWith(getSignKey())
				.compact();
	}
	
	public String exactEmai(String token) {
		Claims claims=Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
	
	public boolean isValidToken(String token) {
		
		try {
		Jwts.parserBuilder()
		.setSigningKey(getSignKey())
		.build()
		.parseClaimsJws(token);
		
		return true;
		}
		catch (JwtException e) {
			return false;
		}
	}
	
}
