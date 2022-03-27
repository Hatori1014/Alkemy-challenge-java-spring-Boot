package com.alkemy.challengejava.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genero", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class Genero implements Serializable {

	private static final long serialVersionUID = 88546359177783905L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long genero_id;
	private String nombre;
	private String imagen;

}
