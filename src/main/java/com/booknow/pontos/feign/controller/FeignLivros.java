package com.booknow.pontos.feign.controller;

import com.booknow.pontos.feign.model.LivroTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@FeignClient(name="livrosService", url = "http://localhost:8082")
public interface FeignLivros {

    @GetMapping("/livros/{id}")
    public Optional<LivroTo> findById(@PathVariable("id") Long idLivro);

}