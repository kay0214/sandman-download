package com.sandman.emmmoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EmmmoeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmmmoeApplication.class, args);
    }

}

