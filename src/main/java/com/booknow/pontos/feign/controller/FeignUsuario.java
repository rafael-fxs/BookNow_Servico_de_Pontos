package com.booknow.pontos.feign.controller;

import com.booknow.pontos.feign.model.UsuarioTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@FeignClient(name="usuarioService", url = "http://localhost:8081")
public interface FeignUsuario {

    @GetMapping("/Usuario/{id}")
    Optional<UsuarioTo> findById(@PathVariable("id") int id);

    @PostMapping(value="/Usuario/{id}/pontos/{qtdPontos}", consumes = "application/json")
    void atualizaPontos(@PathVariable("id") int id, @PathVariable("qtdPontos") int qtdPontos);
}