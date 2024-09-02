package com.booknow.pontos.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private User user;

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