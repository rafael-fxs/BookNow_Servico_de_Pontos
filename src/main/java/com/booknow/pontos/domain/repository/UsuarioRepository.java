package com.booknow.pontos.domain.repository;

import com.booknow.pontos.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}