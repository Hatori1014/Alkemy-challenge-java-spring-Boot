package com.alkemy.challengejava.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.challengejava.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByCorreo(String correo);

	public Optional<Usuario> findByUsuarioOrCorreo(String usuario, String correo);

	public Optional<Usuario> findByUsuario(String usuario);

	public Boolean existsByusuario(String usuario);

	public Boolean existsByCorreo(String correo);
}
