package com.alkemy.challengejava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alkemy.challengejava.models.Personaje;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

	Personaje findPersonajeByNombre(String nombre);

	@Query(value = "SELECT p FROM Personaje p WHERE  p.nombre LIKE CONCAT(:nombrePersonaje,'%')")
	List<Personaje> filtrarPersonajePorNombre(@Param("nombrePersonaje") String nombre);

	@Query(value = "SELECT p FROM Personaje p WHERE p.edad = :edadPersonaje")
	List<Personaje> filtrarPersonajePorEdad(@Param("edadPersonaje") Integer edadPersonaje);

	@Query(value = "SELECT per FROM Personaje per WHERE per.personaje_id IN(SELECT pel FROM Pelicula pel WHERE pel.pelicula_id = :idPelicula)")
	List<Personaje> filtrarPersonajePorPelicula(@Param("idPelicula") Long idPelicula);

}
