package com.jteam.project_2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project2Application {

    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> System.out.println("Hello");
    }

}
