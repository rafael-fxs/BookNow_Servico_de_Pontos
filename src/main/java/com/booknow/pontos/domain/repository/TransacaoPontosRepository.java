package com.booknow.pontos.domain.repository;

import com.booknow.pontos.domain.model.TransacaoPontos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoPontosRepository extends JpaRepository<TransacaoPontos, Long> {
}