package com.example.googlebooksapi;

import com.example.googlebooksapi.ConsoleIO.ConsoleApiInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoogleBooksApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoogleBooksApiApplication.class, args);
        ConsoleApiInfo.print();
    }

}
