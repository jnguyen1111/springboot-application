package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //this function serves as a restful endpoint given annotation with the rest controller serves as the POST request
    @PostMapping
    //request body is that we take a http request and from its request body we map it to the parameter given and then call the book service to add new book
    public void registerNewBook(@RequestBody Book book){ BookService.addNewBook(book);}

    //this function serves as a restful endpoint given annotation with the rest controller serves as the Delete request
    @DeleteMapping(path = "{bookOrder}")
    public void deleteBook(@PathVariable("bookOrder") Integer bookOrder){BookService.deleteBook(bookOrder);}

}
