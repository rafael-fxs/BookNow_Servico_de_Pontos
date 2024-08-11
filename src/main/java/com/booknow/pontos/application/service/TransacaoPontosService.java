package com.booknow.pontos.application.service;

import com.booknow.pontos.domain.model.TipoTransacao;
import com.booknow.pontos.domain.model.TransacaoPontos;
import com.booknow.pontos.domain.model.Usuario;
import com.booknow.pontos.domain.repository.TransacaoPontosRepository;
import com.booknow.pontos.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TransacaoPontosService {

    @Autowired
    private TransacaoPontosRepository transacaoPontosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra uma nova transação de pontos para um usuário.
     * Valida o saldo do usuário antes de permitir uma transação de gasto.
     *
     * @param transacao a transação a ser registrada
     * @throws IllegalArgumentException se o usuário não for encontrado ou se o saldo for insuficiente
     */
    @Transactional
    public void registrarTransacao(TransacaoPontos transacao) {
        Usuario usuario = usuarioRepository.findById(transacao.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Valida se há saldo suficiente em caso de gasto
        if (transacao.getTipoTransacao() == TipoTransacao.GASTO) {
            if (usuario.getSaldoPontos() < transacao.getPontos()) {
                throw new IllegalArgumentException("Saldo insuficiente para realizar a transação.");
            }
            usuario.setSaldoPontos(usuario.getSaldoPontos() - transacao.getPontos());
        } else {
            usuario.setSaldoPontos(usuario.getSaldoPontos() + transacao.getPontos());
        }

        usuarioRepository.save(usuario);
        transacaoPontosRepository.save(transacao);
    }

    /**
     * Consulta o saldo de pontos de um usuário.
     *
     * @param usuarioId o ID do usuário
     * @return o saldo de pontos do usuário
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public int consultarSaldo(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return usuario.getSaldoPontos();
    }

    /**
     * Consulta o histórico de transações de um usuário.
     *
     * @param usuarioId o ID do usuário
     * @return a lista de transações do usuário
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public List<TransacaoPontos> consultarHistoricoTransacoes(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return transacaoPontosRepository.findByUsuario(usuario);
    }
}