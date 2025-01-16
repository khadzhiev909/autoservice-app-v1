package com.example.newpartssertvice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NewPartsSertviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewPartsSertviceApplication.class, args);
    }

}
