package com.mballem.curso.jasper.spring.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {



    private static final String JASPER_DIRETORIO = "classpath:jasper/";
    private static final String JASPER_PREFIXO = "funcionarios-";
    private static final String JASPER_SUFIXO = ".jasper";

    public JasperService(){
        this.params.put("IMAGEM_DIRETORIO", JASPER_DIRETORIO);
    }

    @Autowired
    private Connection conn;

    private Map<String, Object> params = new HashMap<>();

    public void addParams(String key, Object value){

        this.params.put(key, value);
    }

    public byte[] exportarPDF(String code) throws FileNotFoundException, JRException {

        byte[] bytes = null;
        File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
        JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, conn);
        bytes = JasperExportManager.exportReportToPdf(print);

        return bytes;
    }

    public HtmlExporter exportarHTML(String code, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
        HtmlExporter htmlExporter = null;
        File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
        JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, conn);
        htmlExporter = new HtmlExporter();
        htmlExporter.setExporterInput(new SimpleExporterInput(print));
        htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        return htmlExporter;
    }
}
