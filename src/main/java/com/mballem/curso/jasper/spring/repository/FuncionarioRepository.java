package com.mballem.curso.jasper.spring.repository;

import com.mballem.curso.jasper.spring.entity.Funcionario;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select f.id, f.nome from Funcionario f "
           + " where f.nome like %:nome% and f.dataDemissao is not null "
           + " and f.nivel.id = 1")
    List<Tuple> findFuncionariosByNome(String nome);

}
