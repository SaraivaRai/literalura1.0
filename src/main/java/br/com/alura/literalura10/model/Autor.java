package br.com.alura.literalura10.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;

    private Integer anoDeNascimento;

    private Integer anoDeFalecimento;

    @ManyToOne
    private Livro livro;

    public Autor() {}

    public Autor(DadosAutor dadosAutor, Livro livro) {
        if (dadosAutor.autor().contains(",")) {
            String[] autor = dadosAutor.autor().split(", ");
            this.autor = autor[1] + " " + autor[0];
        } else {
            this.autor = dadosAutor.autor();
        }
        this.anoDeNascimento = dadosAutor.anoDeNascimento();
        this.anoDeFalecimento = dadosAutor.anoDeFalecimento();
        this.livro = livro;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(Integer anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return
                "autor='" + autor + '\'' +
                        ", anoDeNascimento=" + anoDeNascimento +
                        ", anoDeFalecimento=" + anoDeFalecimento;

    }
}