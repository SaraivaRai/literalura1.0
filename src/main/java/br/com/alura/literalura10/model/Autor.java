package br.com.alura.literalura10.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;

    private Year anoDeNascimento;

    private Year anoDeFalecimento;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoDeNascimento = Year.of(dadosAutor.anoDeNascimento());
        this.anoDeFalecimento = Year.of(dadosAutor.anoDeFalecimento());
       }

    public Autor(String autor, Year anoDeNascimento, Year anoDeFalecimento) {
        this.nome = nome;
        this.anoDeNascimento = anoDeNascimento;
        this.anoDeFalecimento = anoDeFalecimento;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return
            "**************** AUTOR ****************" +
                "\n nome = '" + nome + '\'' +
                "\n anoDeNascimento = " + anoDeNascimento +
                "\n anoDeFalecimento = " + anoDeFalecimento +
                "\n Livros = " + livros.stream()
                                    .map(l -> l.getTitulo())
                               .collect(Collectors.toList()) +
            "\n---------------------------------------";
    }

}