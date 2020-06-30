package com.backend.Contronllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class BackendApplication {
    @RequestMapping("/")
    public String init(){
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class);
    }
}
