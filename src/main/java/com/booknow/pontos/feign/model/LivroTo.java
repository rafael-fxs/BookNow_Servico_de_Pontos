package com.booknow.pontos.feign.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroTo {
    private Long idLivro;
    private String NomeLivro;
    private Integer pontos;
}