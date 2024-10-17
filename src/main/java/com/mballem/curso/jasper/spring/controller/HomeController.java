package com.mballem.curso.jasper.spring.controller;

import com.mballem.curso.jasper.spring.entity.Funcionario;
import com.mballem.curso.jasper.spring.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @Autowired
    public Connection conn;

    @GetMapping(value = "/lista")
    public List<Funcionario> findAll() {
        return funcionarioService.findAll();
    }

    @GetMapping("{id}")
    public Funcionario getFuncionario(@PathVariable Long id){
        return funcionarioService.findById(id);
    }

    @GetMapping("/con")
    public String conexao(Model model){
        model.addAttribute("con", conn != null ? "Conexão ok!!!":"Erro");
        return "index";
    }
}
