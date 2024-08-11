package com.booknow.pontos.web;

import com.booknow.pontos.application.service.TransacaoPontosService;
import com.booknow.pontos.domain.model.TransacaoPontos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoPontosController {

    @Autowired
    private TransacaoPontosService transacaoPontosService;

    @PostMapping
    public ResponseEntity<Void> registrarTransacao(@RequestBody TransacaoPontos transacao) {
        transacaoPontosService.registrarTransacao(transacao);
        return ResponseEntity.ok().build();
    }

    // Outros endpoints para histórico e saldo
}