package com.booknow.pontos.web;

import com.booknow.pontos.domain.model.User;
import com.booknow.pontos.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulacao")
public class SimulacaoController {

    @Autowired
    private UserRepository UserRepository;

    @PostMapping("/usuario/{id}")
    public ResponseEntity<User> simularUser(@PathVariable int id) {
        User User = new User();
        User.setId(id);
        User.setCpf("00000000000");
        User.setNome("Usuário Simulado " + id);
        User.setEmail("simulado" + id + "@exemplo.com");
        User.setSenha("123456");
        User.setTotalPontos(0);

        UserRepository.save(User);
        return ResponseEntity.ok(User);
    }
}
