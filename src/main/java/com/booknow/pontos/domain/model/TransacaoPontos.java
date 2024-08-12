package com.booknow.pontos.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes_pontos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoPontos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private Long livroId;

    private int pontos;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    private LocalDateTime dataTransacao = LocalDateTime.now();
}