package com.alkemy.challengejava.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumenPeliculaDTO {

	private String imagen;
	private String titulo;
	private LocalDate fechaCreacion;

}
