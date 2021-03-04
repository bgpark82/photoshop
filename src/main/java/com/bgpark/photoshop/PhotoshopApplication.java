package com.bgpark.photoshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PhotoshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoshopApplication.class, args);
    }

}
