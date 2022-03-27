package com.alkemy.challengejava.dto;

import java.time.LocalDate;
import java.util.List;

import com.alkemy.challengejava.models.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaDTO {

	private Long pelicula_id;
	private String imagen;
	private String titulo;
	private LocalDate fechaCreacion = LocalDate.now();
	private Integer calificacion;
	private List<Genero> generos; 

}
