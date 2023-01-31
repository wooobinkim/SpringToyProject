package com.compnay.stp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StpApplication {
    public static void main(String[] args) {
        SpringApplication.run(StpApplication.class);
    }
}