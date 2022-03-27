package com.alkemy.challengejava.exceptions;

import org.springframework.http.HttpStatus;

public class ChallengeAlmekyAppException extends RuntimeException {

	private static final long serialVersionUID = 3649443753639246401L;

	private HttpStatus estado;
	private String mensaje;

	public ChallengeAlmekyAppException(HttpStatus estado, String mensaje) {
		this.estado = estado;
		this.mensaje = mensaje;
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
