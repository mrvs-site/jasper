package com.mballem.curso.jasper.spring.controller;

import com.mballem.curso.jasper.spring.entity.Funcionario;
import com.mballem.curso.jasper.spring.entity.Nivel;
import com.mballem.curso.jasper.spring.repository.FuncionarioRepository;
import com.mballem.curso.jasper.spring.service.EnderecoService;
import com.mballem.curso.jasper.spring.service.JasperService;
import com.mballem.curso.jasper.spring.service.NivelService;
import jakarta.persistence.Tuple;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class JasperController {

    @Autowired
    private JasperService jasperService;


    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private NivelService nivelService;


    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @GetMapping("/reports")
    public String abrir() {
        return "reports";
    }

    @GetMapping("/relatorio/pdf/jr1")
    public void exibirRelatorio(@RequestParam String code,
                                @RequestParam String acao,
                                HttpServletResponse response) throws JRException, IOException {

        byte[] bytes = jasperService.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        if (acao.equals("v")) {
            response.setHeader("Content-disposition", "inline; filename=relatorio-" + code + ".pdf");
        } else {
            response.setHeader("Content-disposition", "attachment; filename=relatorio-" + code + ".pdf");
        }

        response.getOutputStream().write(bytes);
    }

    @GetMapping("/relatorio/pdf/jr9/{code}")
    public void exibirRelatorio09(@PathVariable String code,
                                  @RequestParam String nivel,
                                  @RequestParam String uf,
                                  HttpServletResponse response) throws JRException, IOException {


        jasperService.addParams("NIVEL_DESC", nivel.isEmpty() ? null : nivel);
        jasperService.addParams("UF", uf.isEmpty() ? null : uf);
        byte[] bytes = jasperService.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition",   "inline; filename=relatorio-" + code + ".pdf");

        response.getOutputStream().write(bytes);
    }

    @GetMapping("/relatorio/pdf/jr19/{code}")
    public void exibirRelatorio09(@PathVariable String code,
                                  @RequestParam Long id,
                                  HttpServletResponse response) throws JRException, IOException {

        jasperService.addParams("ID_FUNCIONARIO", id);

        byte[] bytes = jasperService.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition",   "inline; filename=relatorio-" + code + ".pdf");

        response.getOutputStream().write(bytes);
    }

    @GetMapping("/relatorio/html/jr19/{code}")
    public void exibirRelatorio09HTML(@PathVariable String code,
                                      @RequestParam Long id,
                                      HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {

        response.setContentType(MediaType.TEXT_HTML_VALUE);
        jasperService.addParams("ID_FUNCIONARIO", id);
        jasperService.exportarHTML(code, request, response).exportReport();
    }


    @ModelAttribute("niveis")
    public List<String> getNiveis(){
        List<String> a = nivelService.findAll();
        return  nivelService.findAll();
    }


    @ModelAttribute("uf")
    public List<String> getUF(){
        List<String> a = enderecoService.lista();
        return enderecoService.lista();
    }

    @GetMapping("/buscar/funcionarios")
    public ModelAndView buscaFuncionarios(@RequestParam String nome){

        List<Tuple> fun =  funcionarioRepository.findFuncionariosByNome(nome);

        return new ModelAndView("reports", "funcionarios",
                funcionarioRepository.findFuncionariosByNome(nome));

    }


}
