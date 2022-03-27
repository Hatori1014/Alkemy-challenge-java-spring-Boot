package com.alkemy.challengejava.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.challengejava.dto.JwtAuthResponseDTO;
import com.alkemy.challengejava.dto.LoginDTO;
import com.alkemy.challengejava.dto.RegistroDTO;
import com.alkemy.challengejava.models.Rol;
import com.alkemy.challengejava.models.Usuario;
import com.alkemy.challengejava.repositories.RolRepository;
import com.alkemy.challengejava.repositories.UsuarioRepository;
import com.alkemy.challengejava.security.JwtTokenProvider;
import com.alkemy.challengejava.sengrid.EmailService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private EmailService emailService;

	@PostMapping("/auth/login")
	public ResponseEntity<JwtAuthResponseDTO> autenticarUsuario(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUsuarioOrCorreo(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// obtener token del Jwtprovider
		String token = jwtTokenProvider.generarToken(authentication);

		return ResponseEntity.ok(new JwtAuthResponseDTO(token));

	}

	@PostMapping("/auth/register")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {

		String response = emailService.sendEmail(registroDTO.getCorreo());

		if (usuarioRepository.existsByusuario(registroDTO.getUsuario())) {
			return new ResponseEntity<String>("Nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		if (usuarioRepository.existsByCorreo(registroDTO.getCorreo())) {
			return new ResponseEntity<String>("El correo del usuario ya existe", HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsuario(registroDTO.getUsuario());
		usuario.setCorreo(registroDTO.getCorreo());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

		Rol roles = rolRepository.findByNombre("ROLE_ADMIN").get();

		usuario.setRoles(Collections.singleton(roles));

		usuarioRepository.save(usuario);

		return new ResponseEntity<String>("Usuario registrado correctamente", HttpStatus.OK);

	}

}
