package com.mballem.curso.jasper.spring.service;

import com.mballem.curso.jasper.spring.entity.Funcionario;
import com.mballem.curso.jasper.spring.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    public FuncionarioRepository funcionarioRepository;


    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id){
        return funcionarioRepository.findById(id).orElseThrow(null);
    }
}
