package com.alkemy.challengejava.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alkemy.challengejava.models.Rol;
import com.alkemy.challengejava.models.Usuario;
import com.alkemy.challengejava.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String usuarioOrCorreo) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsuarioOrCorreo(usuarioOrCorreo, usuarioOrCorreo)
				.orElseThrow(() -> new UsernameNotFoundException(
						"Usuario no encontrado con ese nombre de usuario o correo suministrado: " + usuarioOrCorreo));
		return new User(usuario.getCorreo(), usuario.getPassword(), mapearRoles(usuario.getRoles() ));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles) {
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
}
