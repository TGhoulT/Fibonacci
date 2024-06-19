package com.example.myfibonacciapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.myfibonacciapp"})
public class MyFibonacciAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFibonacciAppApplication.class, args);
    }
}
