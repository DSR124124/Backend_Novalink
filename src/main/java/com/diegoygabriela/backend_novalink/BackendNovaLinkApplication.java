package com.diegoygabriela.backend_novalink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.diegoygabriela.backend_novalink")
public class BackendNovaLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendNovaLinkApplication.class, args);
    }

}
