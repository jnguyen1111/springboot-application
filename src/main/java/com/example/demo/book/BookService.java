package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//marks class as a service component encapsulate buisness logic and perform task
//
//inside class is the service implementation
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {this.bookRepository = bookRepository;}

    //Calls hibernate to perform sql statement
    public List<Book> getBooks(){return bookRepository.findAll();}

    public void addNewBook(Book book){
        Optional<Book> bookTitle = bookRepository.findBookByTitle(book.getTitle());
        if(bookTitle.isPresent()){
            throw new IllegalStateException("Book already exists!");
        }
        bookRepository.save(book);
        System.out.println(book);

    }

    public void deleteBook(Integer bookOrder){
        boolean exists = bookRepository.existsById(bookOrder);
        if (!exists){throw new IllegalStateException("Book with bookOrder " + bookOrder + " does not exists");}
        bookRepository.deleteById(bookOrder);
    }
}
