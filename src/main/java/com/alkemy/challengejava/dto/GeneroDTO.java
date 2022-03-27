package com.alkemy.challengejava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneroDTO {

	private Long genero_id;
	private String nombre;
	private String imagen;

}
