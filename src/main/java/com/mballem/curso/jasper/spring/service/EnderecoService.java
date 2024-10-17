package com.mballem.curso.jasper.spring.service;

import com.mballem.curso.jasper.spring.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;


    public List<String> lista(){
        return  enderecoRepository.findUfs();
    }
}
