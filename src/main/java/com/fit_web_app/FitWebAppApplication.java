package com.fit_web_app;

import com.fit_web_app.configuration.AbstractDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FitWebAppApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        AbstractDao.init();
        SpringApplication.run(FitWebAppApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FitWebAppApplication.class);
    }
}