package com.alkemy.challengejava.services;

import java.util.List;

import com.alkemy.challengejava.dto.PersonajeDTO;
import com.alkemy.challengejava.dto.ResumenPersonajeDTO;

public interface PersonajeService {

	public List<ResumenPersonajeDTO> listarPersonajes();

	public PersonajeDTO detallePersonaje(String nombrePersonaje);

	public List<PersonajeDTO> filtrarPersonaje(String nombrePersonaje, Integer edadPersonaje, Long idPelicula);

	public PersonajeDTO crearPersonaje(PersonajeDTO personaje);

	public PersonajeDTO actualizarPersonaje(Long idPersonaje, PersonajeDTO personaje);

	public String eliminarPersonaje(Long idPersonaje);

}
