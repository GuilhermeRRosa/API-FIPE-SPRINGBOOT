package com.vecon.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vecon.models.Usuario;
import com.vecon.repositories.UsuarioRepository;
import com.vecon.services.exceptions.ObjectNotFoundException;

/**Classe para de Serviço para usuário.
* @author Guilherme R Rosa
*/

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id: "+id));
	}
	
	public boolean verifyUserExist(Long id) {
		if(!usuarioRepository.existsById(id))
			throw new ObjectNotFoundException("Usuário não encontrado! Id: "+id);
		return true; 
	}
	
	public void saveUsuario(Usuario usuario) {
			usuarioRepository.save(usuario);		
	}
	
}
