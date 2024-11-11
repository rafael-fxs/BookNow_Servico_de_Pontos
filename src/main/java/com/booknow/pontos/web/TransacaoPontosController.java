package com.booknow.pontos.web;

import com.booknow.pontos.service.TransacaoPontosService;
import com.booknow.pontos.domain.model.TransacaoPontos;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoPontosController {

    @Autowired
    private TransacaoPontosService transacaoPontosService;

    @PostMapping
    public ResponseEntity<Void> registrarTransacao(@RequestBody TransacaoPontos transacao) throws JsonProcessingException {
        transacaoPontosService.registrarTransacao(transacao);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/saldo/{UserId}")
    public ResponseEntity<Integer> consultarSaldo(@PathVariable int UserId) {
        int saldo = transacaoPontosService.consultarSaldo(UserId);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/historico/{UserId}")
    public ResponseEntity<List<TransacaoPontos>> consultarHistoricoTransacoes(@PathVariable int UserId) {
        List<TransacaoPontos> historico = transacaoPontosService.consultarHistoricoTransacoes(UserId);
        return ResponseEntity.ok(historico);
    }
    @GetMapping("/teste")
    public String helloWorld() {
        return "Hello World";
    }

}