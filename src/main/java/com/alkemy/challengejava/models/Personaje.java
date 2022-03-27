package com.alkemy.challengejava.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personaje", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class Personaje implements Serializable {

	private static final long serialVersionUID = -4890812868903586589L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long personaje_id;
	private String nombre;
	private Integer edad;
	private Long peso;
	private String historia;
	private String imagen;

	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.MERGE, CascadeType.SAVE_UPDATE })
	@JoinTable(name = "personaje_pelicula", joinColumns = @JoinColumn(name = "personaje_id"), inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
	private Set<Pelicula> peliculas = new HashSet<>();

	public void agregarPelicula(Pelicula pelicula) {
		this.peliculas.add(pelicula);
	}

}
