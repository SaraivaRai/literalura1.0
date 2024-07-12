package br.com.alura.literalura10.repository;

import br.com.alura.literalura10.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface LivroRepository extends JpaRepository<Livro, Long> {

        List<Livro> findByIdioma(String Idioma);

        Integer countByIdioma(String Idioma);

       // List<Livro> findTop10ByOrderByNumeroDownloadsDesc();
    }

