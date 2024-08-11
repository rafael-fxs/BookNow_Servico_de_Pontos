package com.booknow.pontos.application.service;

import com.booknow.pontos.domain.model.TipoTransacao;
import com.booknow.pontos.domain.model.TransacaoPontos;
import com.booknow.pontos.domain.model.Usuario;
import com.booknow.pontos.domain.repository.TransacaoPontosRepository;
import com.booknow.pontos.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacaoPontosService {

    @Autowired
    private TransacaoPontosRepository transacaoPontosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void registrarTransacao(TransacaoPontos transacao) {
        Usuario usuario = transacao.getUsuario();
        int novoSaldo = usuario.getSaldoPontos() +
                (transacao.getTipoTransacao() == TipoTransacao.GANHO ? transacao.getPontos() : -transacao.getPontos());

        usuario.setSaldoPontos(novoSaldo);
        usuarioRepository.save(usuario);
        transacaoPontosRepository.save(transacao);
    }
}