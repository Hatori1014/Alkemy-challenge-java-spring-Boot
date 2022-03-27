package com.alkemy.challengejava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.challengejava.dto.PeliculaDTO;
import com.alkemy.challengejava.services.PeliculaService;

@RestController
@RequestMapping("/api/v1/disney/movies")
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;

	@GetMapping("/")
	public ResponseEntity<?> listarPeliculas() {
		return new ResponseEntity<>(peliculaService.listarPeliculas(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> filtrarPelicula(
			@RequestParam(name = "name", required = false) String nombrePelicula,
			@RequestParam(name = "genre", required = false) Long idGenero,
			@RequestParam(name = "order", required = false) String orden) {
		return new ResponseEntity<>(peliculaService.filtrarPelicula(nombrePelicula, idGenero, orden), HttpStatus.OK);
	}

	@GetMapping("/{tituloPelicula}")
	public ResponseEntity<?> detallePelicula(@PathVariable String tituloPelicula) {
		PeliculaDTO peliculaDetallada = peliculaService.detallePelicula(tituloPelicula);
		if (peliculaDetallada == null) {
			return new ResponseEntity<>("Pelicula no existe", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(peliculaService.detallePelicula(tituloPelicula), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<?> crearPelicula(@RequestBody PeliculaDTO pelicula) {
		return new ResponseEntity<>(peliculaService.crearPelicula(pelicula), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{idPelicula}")
	public ResponseEntity<?> actualizarPelicula(@PathVariable("idPelicula") Long idPelicula,
			@RequestBody PeliculaDTO pelicula) {
		return new ResponseEntity<>(peliculaService.actualizarPelicula(idPelicula, pelicula), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{idPelicula}")
	public ResponseEntity<?> eliminarPelicula(@PathVariable("idPelicula") Long idPelicula) {
		return new ResponseEntity<>(peliculaService.eliminarPelicula(idPelicula), HttpStatus.OK);
	}

}
