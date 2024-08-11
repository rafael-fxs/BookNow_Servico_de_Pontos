package com.booknow.pontos.web;

import com.booknow.pontos.domain.model.Usuario;
import com.booknow.pontos.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulacao")
public class SimulacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuario/{id}")
    public ResponseEntity<Usuario> simularUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome("Usuário Simulado " + id);
        usuario.setEmail("simulado" + id + "@exemplo.com");
        usuario.setSaldoPontos(0);

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/livro")
    public ResponseEntity<Long> simularLivro() {
        Long livroId = 123L;
        return ResponseEntity.ok(livroId);
    }
}
