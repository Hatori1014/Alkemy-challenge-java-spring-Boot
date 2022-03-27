package com.alkemy.challengejava.dto;

import java.util.List;

import com.alkemy.challengejava.models.Pelicula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeDTO {

	private Long personaje_id;
	private String nombre;
	private Integer edad;
	private Long peso;
	private String historia;
	private String imagen;

	private List<Pelicula> peliculas;

}
