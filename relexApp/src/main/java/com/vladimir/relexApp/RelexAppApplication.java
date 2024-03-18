package com.vladimir.relexApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RelexAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RelexAppApplication.class, args);

    }

}
