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

import com.alkemy.challengejava.dto.PersonajeDTO;
import com.alkemy.challengejava.services.PersonajeService;

@RestController
@RequestMapping("/api/v1/disney/characters")
public class PersonajeController {

	@Autowired
	PersonajeService personajeService;

	@GetMapping("/")
	public ResponseEntity<?> listarPersonajes() {
		return new ResponseEntity<>(personajeService.listarPersonajes(), HttpStatus.OK);
	}

	@GetMapping("/{nombrePersonaje}")
	public ResponseEntity<?> detallePersonaje(@PathVariable String nombrePersonaje) {
		PersonajeDTO personajeDetallado = personajeService.detallePersonaje(nombrePersonaje);
		if (personajeDetallado == null) {
			return new ResponseEntity<>("Personaje no existe", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(personajeService.detallePersonaje(nombrePersonaje), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> filtrarPersonaje(@RequestParam(name = "name", required = false) String nombrePersonaje,
			@RequestParam(name = "age", required = false) Integer edadPersonaje,
			@RequestParam(name = "movies", required = false) Long idPelicula) {
		return new ResponseEntity<>(personajeService.filtrarPersonaje(nombrePersonaje, edadPersonaje, idPelicula),
				HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<?> crearPersonaje(@RequestBody PersonajeDTO personaje) {
		return new ResponseEntity<>(personajeService.crearPersonaje(personaje), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{idPersonaje}")
	public ResponseEntity<?> actualizarParsonaje(@PathVariable("idPersonaje") Long idPersonaje,
			@RequestBody PersonajeDTO personaje) {
		return new ResponseEntity<>(personajeService.actualizarPersonaje(idPersonaje, personaje), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{idPersonaje}")
	public ResponseEntity<?> eliminarParsonaje(@PathVariable("idPersonaje") Long idPersonaje) {
		return new ResponseEntity<>(personajeService.eliminarPersonaje(idPersonaje), HttpStatus.OK);
	}

}
