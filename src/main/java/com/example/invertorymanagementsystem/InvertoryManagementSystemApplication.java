package com.example.invertorymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InvertoryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvertoryManagementSystemApplication.class, args);
    }

}
