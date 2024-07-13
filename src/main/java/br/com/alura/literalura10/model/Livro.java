package br.com.alura.literalura10.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Double downloads;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Livro() {}

   public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        Autor autor = new Autor(dadosLivro.autor().get(0));
        this.autor = autor;
        this.idioma = dadosLivro.idioma().get(0);
        this.downloads = dadosLivro.downloads();
        }
   public Livro(String titulo, Autor autor, String idioma, Double downloads){
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.downloads = downloads;
   }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return
            "**************** LIVRO ****************" +
                  "\n  titulo = '" + titulo +
                  "\n  Autor = " + autor.getNome() +
                  "\n  lingua = " + idioma +
                  "\n  downloads = " + downloads +
            "\n-------------------------------------\n";
    }
}

