package com.alkemy.challengejava.services;

import java.util.List;

import com.alkemy.challengejava.dto.PeliculaDTO;
import com.alkemy.challengejava.dto.ResumenPeliculaDTO;

public interface PeliculaService {
	
	public List<ResumenPeliculaDTO> listarPeliculas();

	public PeliculaDTO detallePelicula(String nombrePelicula);
	
	public List<PeliculaDTO> filtrarPelicula(String nombrePelicula, Long idGenero, String orden);
		
	public PeliculaDTO crearPelicula(PeliculaDTO pelicula);

	public PeliculaDTO actualizarPelicula(Long idPelicula, PeliculaDTO pelicula);

	public String eliminarPelicula(Long idPelicula);
}
