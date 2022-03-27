package com.alkemy.challengejava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.challengejava.models.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

	public Optional<Rol> findByNombre(String nombre);

}
