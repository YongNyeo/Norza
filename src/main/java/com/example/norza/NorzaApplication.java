package com.example.norza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NorzaApplication {
    public static void main(String[] args)  {
        SpringApplication.run(NorzaApplication.class, args);

    }
}
