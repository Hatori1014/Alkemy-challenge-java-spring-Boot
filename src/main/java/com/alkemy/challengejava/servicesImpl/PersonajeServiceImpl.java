package com.alkemy.challengejava.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.dto.PersonajeDTO;
import com.alkemy.challengejava.dto.ResumenPersonajeDTO;
import com.alkemy.challengejava.models.Personaje;
import com.alkemy.challengejava.repositories.PersonajeRepository;
import com.alkemy.challengejava.services.PersonajeService;

@Service
public class PersonajeServiceImpl implements PersonajeService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PersonajeRepository personajeRepository;

	@Override
	public List<ResumenPersonajeDTO> listarPersonajes() {
		List<Personaje> listadoPersonajes = personajeRepository.findAll();
		List<ResumenPersonajeDTO> resultadoPersonajes = new ArrayList<ResumenPersonajeDTO>();

		listadoPersonajes.forEach(personaje -> {
			resultadoPersonajes.add(modelMapper.map(personaje, ResumenPersonajeDTO.class));
		});

		return resultadoPersonajes;
	}

	@Override
	public PersonajeDTO detallePersonaje(String nombrePersonaje) {
		Personaje personaje = personajeRepository.findPersonajeByNombre(nombrePersonaje);
		return modelMapper.map(personaje, PersonajeDTO.class);
	}

	@Override
	public List<PersonajeDTO> filtrarPersonaje(String nombrePersonaje, Integer edadPersonaje, Long idPelicula) {
		List<Personaje> personajes = new ArrayList<>();
		List<PersonajeDTO> personajesFiltrados = new ArrayList<>();

		if ((Integer) edadPersonaje == null && (Long)idPelicula == null) {
			personajes = personajeRepository.filtrarPersonajePorNombre(nombrePersonaje);
		}

		if (nombrePersonaje == null && (Long)idPelicula == null) {
			personajes = personajeRepository.filtrarPersonajePorEdad(edadPersonaje);
		}

		if (nombrePersonaje == null && (Integer)edadPersonaje == null) {
			personajes = personajeRepository.filtrarPersonajePorPelicula(idPelicula);
		}

		personajes.forEach(personaje -> personajesFiltrados.add(modelMapper.map(personaje, PersonajeDTO.class)));
		return personajesFiltrados;
	}

	@Override
	public PersonajeDTO crearPersonaje(PersonajeDTO personaje) {
		Personaje nuevoPersonaje = modelMapper.map(personaje, Personaje.class);
		personajeRepository.save(nuevoPersonaje);
		return modelMapper.map(nuevoPersonaje, PersonajeDTO.class);
	}

	@Override
	public PersonajeDTO actualizarPersonaje(Long idPersonaje, PersonajeDTO personaje) {
		Personaje personajeExiste = personajeRepository.findById(idPersonaje).orElse(null);
		if (personajeExiste == null)
			return null;

		personajeExiste.setEdad(personaje.getEdad());
		personajeExiste.setHistoria(personaje.getHistoria());
		personajeExiste.setNombre(personaje.getNombre());
		personajeExiste.setPeso(personaje.getPeso());

		personajeExiste.getPeliculas().forEach(pelicula -> {
			personaje.getPeliculas().forEach(peliculaDTO -> {
				if (!pelicula.getTitulo().equals(peliculaDTO.getTitulo())) {
					personajeExiste.agregarPelicula(pelicula);
				}
			});
		});

		Personaje personajeActualizado = personajeRepository.save(personajeExiste);

		return modelMapper.map(personajeActualizado, PersonajeDTO.class);
	}

	@Override
	public String eliminarPersonaje(Long idPersonaje) {
		Personaje personajeEliminado = personajeRepository.findById(idPersonaje).orElse(null);
		if (personajeEliminado == null)
			return "El personaje no existe";
		personajeRepository.delete(personajeEliminado);
		return "Personaje eliminado correctamente";

	}

}
