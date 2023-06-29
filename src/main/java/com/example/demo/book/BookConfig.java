package com.example.demo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository){
        return args -> {
            Book titanic = new Book(
                    10.00,
                    "titanic",
                    "jeffrey",
                    LocalDate.of(2000, Month.JANUARY,5)
            );
            Book france = new Book(
                    15.00,
                    "france",
                    "jackson",
                    LocalDate.of(2005, Month.FEBRUARY,10)
            );
            repository.saveAll(List.of(titanic,france));
        };
    }
}
