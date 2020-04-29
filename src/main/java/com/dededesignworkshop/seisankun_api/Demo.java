package com.dededesignworkshop.seisankun_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Demo {

    @RequestMapping("/")
    String index(){
        return "Hello!! I'm SeisanKunApi";
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo.class, args);
    }

}