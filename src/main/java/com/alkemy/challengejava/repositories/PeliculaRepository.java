package com.alkemy.challengejava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alkemy.challengejava.models.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

	Pelicula findPeliculaByTitulo(String titulo);

	@Query(value = "SELECT p FROM Pelicula p WHERE  p.titulo LIKE CONCAT(:nombrePelicula,'%')")
	List<Pelicula> filtrarPeliculaPorNombre(@Param("nombrePelicula") String nombrePelicula);

	@Query(value = "SELECT p FROM Pelicula p ORDER BY :orden")
	List<Pelicula> filtrarPeliculaPorOrden(@Param("orden") String orden);

	@Query(value = "SELECT * FROM pelicula as p JOIN pelicula_genero as pg on p.pelicula_id = pg.pelicula_id join GENERO as g on pg.genero_id = g.genero_id where g.genero_id = :idGenero", nativeQuery = true)
	List<Pelicula> filtrarPeliculaPorGenero(@Param("idGenero") Long idGenero);

}
