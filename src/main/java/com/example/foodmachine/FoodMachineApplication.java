package com.example.foodmachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
//@ComponentScan(basePackages = {"com.example.foodmachine"})
@SpringBootApplication
public class FoodMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodMachineApplication.class, args);

    }
}
