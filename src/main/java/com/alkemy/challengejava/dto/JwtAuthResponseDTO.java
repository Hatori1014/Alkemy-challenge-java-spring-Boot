package com.alkemy.challengejava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponseDTO {

	private String tokenAcceso;
	private String tipoToken = "Bearer";

	public JwtAuthResponseDTO(String tokenAcceso) {
		this.tokenAcceso = tokenAcceso;
	}

}
