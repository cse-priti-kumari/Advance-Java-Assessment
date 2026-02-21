package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StorageServiceSystemSpringApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(StorageServiceSystemSpringApplication.class, args);
        StorageService storage = context.getBean(StorageService.class);
        storage.storeFile("app.pdf");
        
        StorageService local1 = context.getBean("localStorage", StorageService.class);
        local1.storeFile("spring.txt");
        
        StorageService local2 = context.getBean("localStorage", StorageService.class);
        local2.storeFile("springboot.txt");
    }
}