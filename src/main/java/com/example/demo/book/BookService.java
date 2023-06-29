package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//marks class as a service component encapsulate buisness logic and perform task
//
//inside class is the service implementation
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Calls hibernate to perform sql statement
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
}
