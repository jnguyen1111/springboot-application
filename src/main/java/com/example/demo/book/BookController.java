package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController //marks class as a restful controller handles incoming request
@RequestMapping(path = "api/v1/book") //specifies a base url path added on to the methods below
public class BookController {
    private final BookService BookService;

    //annotation autowired attempts to find a suitable bean to do dependency injection to bookservice variable above
    //when the bookcontroller is instantiated and the constructor is called
    @Autowired
    public BookController(BookService bookService) {this.BookService = bookService;}

    //this function serves as a restful endpoint given annotation with the rest controller serves as the GET request
    @GetMapping
    public List<Book> getBooks(){return BookService.getBooks();}
}
