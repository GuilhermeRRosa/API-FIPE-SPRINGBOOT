package com.vecon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vecon.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
