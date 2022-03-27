package com.alkemy.challengejava.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.alkemy.challengejava.exceptions.ChallengeAlmekyAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;

	public String generarToken(Authentication authentication) {

		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracionToken = new Date(fechaActual.getTime() + jwtExpirationInMs);

		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracionToken)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		return token;

	}

	public String obtenerUsuarioDelJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public Boolean validarToken(String Token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(Token);
			return true;

		} catch (SignatureException e) {
			throw new ChallengeAlmekyAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
		} catch (MalformedJwtException e) {
			throw new ChallengeAlmekyAppException(HttpStatus.BAD_REQUEST, "Token JWT no valido");
		} catch (ExpiredJwtException e) {
			throw new ChallengeAlmekyAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
		} catch (UnsupportedJwtException e) {
			throw new ChallengeAlmekyAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
		} catch (IllegalArgumentException e) {
			throw new ChallengeAlmekyAppException(HttpStatus.BAD_REQUEST, "La cadena cslaims JWT esta vacia");
		}
	}
}
