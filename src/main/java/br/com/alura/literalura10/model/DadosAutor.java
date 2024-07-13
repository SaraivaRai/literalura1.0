package br.com.alura.literalura10.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Year;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonAlias("name")
        String nome,

        @JsonAlias("birth_year")
        Integer anoDeNascimento,

        @JsonAlias("death_year")
        Integer anoDeFalecimento
) {
}