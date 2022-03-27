package com.alkemy.challengejava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {

	private String nombre;
	private String usuario;
	private String correo;
	private String password;
}
