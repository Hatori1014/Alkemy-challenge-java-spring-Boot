package com.alkemy.challengejava.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pelicula", uniqueConstraints = { @UniqueConstraint(columnNames = { "titulo" }) })
public class Pelicula implements Serializable {

	private static final long serialVersionUID = 5181984928291367151L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pelicula_id;
	private String imagen;
	private String titulo;
	private LocalDate fechaCreacion;

	@Min(1)
	@Max(5)
	private Integer calificacion;

	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL/* CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST */ })
	@JoinTable(name = "pelicula_genero", joinColumns = @JoinColumn(name = "pelicula_id"), inverseJoinColumns = @JoinColumn(name = "genero_id"))
	private List<Genero> generos = new ArrayList<>();

	public void agregarGenero(Genero genero) {
		this.generos.add(genero);
	}

}
