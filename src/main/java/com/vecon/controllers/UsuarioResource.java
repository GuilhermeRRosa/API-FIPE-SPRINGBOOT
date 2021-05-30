package com.vecon.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vecon.models.Usuario;
import com.vecon.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario){
		usuarioService.saveUsuario(usuario);
		return ResponseEntity.created(URI.create("/"+usuario.getId())).body(usuario);
	}
	
	@GetMapping(value = "/{usuario_id}")
	public ResponseEntity<?> findUsuario(@PathVariable Long usuario_id){
		Usuario usuario = usuarioService.findById(usuario_id);
		return ResponseEntity.ok(usuario);
	}
}
