package com.ba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class Run {
    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
        System.out.println("Hello World!");
    }
}
