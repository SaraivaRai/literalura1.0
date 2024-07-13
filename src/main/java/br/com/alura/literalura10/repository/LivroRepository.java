package br.com.alura.literalura10.repository;

import br.com.alura.literalura10.model.Autor;
import br.com.alura.literalura10.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;
import java.util.List;

    public interface LivroRepository extends JpaRepository<Livro, Long> {

        List<Livro> findByIdioma(String idioma);


        @Query("SELECT a FROM Livro l JOIN l.autor a")
        List<Autor> listaDeAutores();

        @Query("SELECT DISTINCT a FROM Autor a WHERE a.anoDeNascimento <= :ano AND (a.anoDeFalecimento IS NULL OR a.anoDeFalecimento >= :ano)")
        List<Autor> findAutoresEmDeterminadoAno(Year ano);



    }

