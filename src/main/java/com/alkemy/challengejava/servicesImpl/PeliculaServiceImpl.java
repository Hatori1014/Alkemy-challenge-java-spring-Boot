package com.alkemy.challengejava.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.PeliculaDTO;
import com.alkemy.challengejava.dto.ResumenPeliculaDTO;
import com.alkemy.challengejava.models.Pelicula;
import com.alkemy.challengejava.repositories.PeliculaRepository;
import com.alkemy.challengejava.services.PeliculaService;

@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	private PeliculaRepository peliculaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ResumenPeliculaDTO> listarPeliculas() {
		List<Pelicula> listadoPeliculas = peliculaRepository.findAll();
		List<ResumenPeliculaDTO> resultadoPeliculas = new ArrayList<ResumenPeliculaDTO>();

		listadoPeliculas.forEach(pelicula -> {
			resultadoPeliculas.add(modelMapper.map(pelicula, ResumenPeliculaDTO.class));
		});

		return resultadoPeliculas;
	}

	@Override
	public List<PeliculaDTO> filtrarPelicula(String nombrePelicula, Long idGenero, String orden) {
		List<Pelicula> peliculas = new ArrayList<>();
		List<PeliculaDTO> peliculasFiltradas = new ArrayList<>();

		if ((Long) idGenero == null && orden == null) {
			peliculas = peliculaRepository.filtrarPeliculaPorNombre(nombrePelicula);
		}

		if (nombrePelicula == null && orden == null) {
			peliculas = peliculaRepository.filtrarPeliculaPorGenero(idGenero);
		}

		if (nombrePelicula == null && (Long) idGenero == null) {
			peliculas = peliculaRepository.filtrarPeliculaPorOrden(orden);
		}

		peliculas.forEach(pelicula -> peliculasFiltradas.add(modelMapper.map(pelicula, PeliculaDTO.class)));
		return peliculasFiltradas;
	}

	@Override
	public PeliculaDTO detallePelicula(String tituloPelicula) {
		Pelicula pelicula = peliculaRepository.findPeliculaByTitulo(tituloPelicula);
		return modelMapper.map(pelicula, PeliculaDTO.class);
	}

	@Override
	public PeliculaDTO crearPelicula(PeliculaDTO pelicula) {
		Pelicula nuevaPelicula = modelMapper.map(pelicula, Pelicula.class);
		peliculaRepository.save(nuevaPelicula);
		return modelMapper.map(nuevaPelicula, PeliculaDTO.class);
	}

	@Override
	public PeliculaDTO actualizarPelicula(Long idPelicula, PeliculaDTO pelicula) {
		Pelicula peliculaExiste = peliculaRepository.findById(idPelicula).orElse(null);
		if (peliculaExiste == null)
			return null;

		peliculaExiste.setTitulo(pelicula.getTitulo());
		peliculaExiste.setImagen(pelicula.getImagen());
		peliculaExiste.setCalificacion(pelicula.getCalificacion());
		peliculaExiste.setFechaCreacion(pelicula.getFechaCreacion());

		peliculaExiste.getGeneros().forEach(genero -> {
			pelicula.getGeneros().forEach(generoRequest -> {
				if (genero.getNombre().equalsIgnoreCase(generoRequest.getNombre())) {
					peliculaExiste.agregarGenero(generoRequest);
				}
			});
		});

		Pelicula peliculaActualizada = peliculaRepository.save(peliculaExiste);

		return modelMapper.map(peliculaActualizada, PeliculaDTO.class);
	}

	@Override
	public String eliminarPelicula(Long idPelicula) {
		Pelicula peliculaEliminada = peliculaRepository.findById(idPelicula).orElse(null);
		if (peliculaEliminada == null)
			return "La pelicula no existe";
		peliculaRepository.delete(peliculaEliminada);
		return "Pelicula eliminada correctamente";
	}

}
