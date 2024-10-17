package com.mballem.curso.jasper.spring.config;

import jakarta.servlet.http.HttpServletRequest;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.springframework.boot.autoconfigure.web.ServerProperties.*;

@Configuration
public class ConfiguracaoGenerica {

    @Bean
    public Connection conexao(DataSource source) throws SQLException {

        return source.getConnection();
    }

    /*@Bean
    public InternalResourceViewResolver imageServlet(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/image/servlet/*");
        resolver.setSuffix(".html");
        resolver.setsetOrder(1);
        return resolver;
    }*/


}
