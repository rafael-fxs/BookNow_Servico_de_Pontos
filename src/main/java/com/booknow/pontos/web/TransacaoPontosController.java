package com.booknow.pontos.web;

import com.booknow.pontos.application.service.TransacaoPontosService;
import com.booknow.pontos.domain.model.TransacaoPontos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/saldo/{usuarioId}")
    public ResponseEntity<Integer> consultarSaldo(@PathVariable Long usuarioId) {
        int saldo = transacaoPontosService.consultarSaldo(usuarioId);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/historico/{usuarioId}")
    public ResponseEntity<List<TransacaoPontos>> consultarHistoricoTransacoes(@PathVariable Long usuarioId) {
        List<TransacaoPontos> historico = transacaoPontosService.consultarHistoricoTransacoes(usuarioId);
        return ResponseEntity.ok(historico);
    }
}