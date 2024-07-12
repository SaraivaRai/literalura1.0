package br.com.alura.literalura10.repository;


import br.com.alura.literalura10.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long>{

    @Query("SELECT a FROM Autor a WHERE :ano <= a.anoDeFalecimento")
    List<Autor> findByAnoDeFalecimentoLessThanEqual(Integer ano);

    List<Autor> findByNomeContainingIgnoreCase(String nome);
}