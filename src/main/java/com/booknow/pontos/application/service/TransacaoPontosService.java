package com.booknow.pontos.application.service;

import com.booknow.pontos.domain.model.TipoTransacao;
import com.booknow.pontos.domain.model.TransacaoPontos;
import com.booknow.pontos.domain.model.User;
import com.booknow.pontos.domain.repository.TransacaoPontosRepository;
import com.booknow.pontos.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransacaoPontosService {

    @Autowired
    private TransacaoPontosRepository transacaoPontosRepository;

    @Autowired
    private UserRepository UserRepository;

    /**
     * Registra uma nova transação de pontos para um usuário.
     * Valida o saldo do usuário antes de permitir uma transação de gasto.
     *
     * @param transacao a transação a ser registrada
     * @throws IllegalArgumentException se o usuário não for encontrado ou se o saldo for insuficiente
     */
    @Transactional
    public void registrarTransacao(TransacaoPontos transacao) {
        User user = UserRepository.findById(transacao.getIdUser())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (transacao.getTipo() == TipoTransacao.GASTO) {
            if (user.getTotalPontos() < transacao.getPontos()) {
                throw new IllegalArgumentException("Saldo insuficiente para realizar a transação.");
            }
            user.setTotalPontos(user.getTotalPontos() - transacao.getPontos());
        } else {
            user.setTotalPontos(user.getTotalPontos() + transacao.getPontos());
        }
        UserRepository.save(user);
        transacao.setUser(user);
        transacaoPontosRepository.save(transacao);
    }

    /**
     * Consulta o saldo de pontos de um usuário.
     *
     * @param UserId o ID do usuário
     * @return o saldo de pontos do usuário
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public int consultarSaldo(int UserId) {
        User User = UserRepository.findById(UserId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return User.getTotalPontos();
    }

    /**
     * Consulta o histórico de transações de um usuário.
     *
     * @param UserId o ID do usuário
     * @return a lista de transações do usuário
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public List<TransacaoPontos> consultarHistoricoTransacoes(int UserId) {
        User User = UserRepository.findById(UserId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return transacaoPontosRepository.findByUser(User);
    }
}