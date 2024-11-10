package com.booknow.pontos.service;

import com.booknow.pontos.domain.model.TipoTransacao;
import com.booknow.pontos.domain.model.TransacaoPontos;
import com.booknow.pontos.domain.repository.TransacaoPontosRepository;
import com.booknow.pontos.feign.controller.FeignLivros;
import com.booknow.pontos.feign.controller.FeignUsuario;
import com.booknow.pontos.feign.model.LivroTo;
import com.booknow.pontos.feign.model.UsuarioTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransacaoPontosService {

    @Autowired
    private TransacaoPontosRepository transacaoPontosRepository;

    @Autowired
    private FeignLivros feignLivros;

    @Autowired
    private FeignUsuario feignUsuario;

    /**
     * Registra uma nova transação de pontos para um usuário.
     * Valida o saldo do usuário antes de permitir uma transação de gasto.
     *
     * @param transacao a transação a ser registrada
     * @throws IllegalArgumentException se o usuário não for encontrado ou se o saldo for insuficiente
     */
    @Transactional
    public void registrarTransacao(TransacaoPontos transacao) {
        UsuarioTo user = feignUsuario.findById(transacao.getIdUser())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        LivroTo livro = feignLivros.findById(transacao.getIdLivro())
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

        Integer pontosAtualizado = user.getTotalPontos();
        if (transacao.getTipo() == TipoTransacao.GASTO) {
            if (pontosAtualizado < livro.getPontos()) {
                throw new IllegalArgumentException("Saldo insuficiente para realizar a transação.");
            }
            pontosAtualizado -= livro.getPontos();
        } else {
            pontosAtualizado += livro.getPontos();
        }
        transacao.setPontos(pontosAtualizado);
        this.atualizaPontos(transacao.getIdUser(), pontosAtualizado);
        transacaoPontosRepository.save(transacao);
    }

    /**
     * Consulta o saldo de pontos de um usuário.
     *
     * @param userId o ID do usuário
     * @return o saldo de pontos do usuário
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public int consultarSaldo(int userId) {
        UsuarioTo user = feignUsuario.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return user.getTotalPontos();
    }

    /**
     * Consulta o histórico de transações de um usuário.
     *
     * @param userId o ID do usuário
     * @return a lista de transações do usuário
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public List<TransacaoPontos> consultarHistoricoTransacoes(int userId) {
        UsuarioTo user = feignUsuario.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return transacaoPontosRepository.findByIdUser(userId);
    }

    private void atualizaPontos(Integer id, Integer pontos) {
        feignUsuario.atualizaPontos(id, pontos);
    }
}