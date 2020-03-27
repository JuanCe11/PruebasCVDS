package edu.eci.superhex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SuperHexAPIApplication {
    public static void main(String[] args) {
        SpringApplication.run(SuperHexAPIApplication.class, args);
    }
}
