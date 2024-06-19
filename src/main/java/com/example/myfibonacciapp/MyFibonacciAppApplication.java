package com.example.myfibonacciapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.myfibonacciapp"}) //это важно (я так думаю, это страховка, с ней все работает :))
public class MyFibonacciAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyFibonacciAppApplication.class, args);
    }
}
