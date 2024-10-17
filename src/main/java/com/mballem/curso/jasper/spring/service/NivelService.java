package com.mballem.curso.jasper.spring.service;

import com.mballem.curso.jasper.spring.entity.Nivel;
import com.mballem.curso.jasper.spring.repository.NivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelService {

    @Autowired
    private NivelRepository nivelRepository;



    public List<String> findAll() {
        return nivelRepository.findNiveis();
    }
}
