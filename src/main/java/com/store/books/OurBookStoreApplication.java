package com.store.books;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Used to mark a configuration class that declares,
one or more @Bean methods and,
also triggers auto-configuration and component scanning.
 */
@SpringBootApplication
// Used to get ability to print over console.
@Slf4j
public class OurBookStoreApplication {

    public static void main(String[] args) {
        log.info("Starting Spring Boot Application!");
        SpringApplication.run(OurBookStoreApplication.class, args);
    }

}