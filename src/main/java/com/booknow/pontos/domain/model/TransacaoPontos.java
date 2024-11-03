package com.booknow.pontos.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pontos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoPontos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPontos", nullable = false)
    private int id;

    @Column(name = "idUser", nullable = false)
    private int idUser;

    @Column(name = "idLivro", nullable = false)
    private Long idLivro;

    @Column(name = "pontos", nullable = false)
    private int pontos;

    @Column(name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao = LocalDateTime.now();
}