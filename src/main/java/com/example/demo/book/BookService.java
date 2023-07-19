package com.example.demo.book;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//marks class as a service component encapsulate buisness logic and perform tasks
//inside class is the service implementation
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {this.bookRepository = bookRepository;}

    //Calls hibernate to perform sql statement
    public List<Book> getAllBooks(){return bookRepository.findAll();}

    public void addNewBook(Book book){
        Optional<Book> bookTitle = bookRepository.findBookByTitle(book.getTitle());
        if(bookTitle.isPresent()){throw new IllegalStateException("Book already exists!");}
        bookRepository.save(book);
    }

    public void deleteBook(Integer bookOrder){
        boolean exists = bookRepository.existsById(bookOrder);
        if (!exists){throw new IllegalStateException("Book with bookOrder " + bookOrder + " does not exists");}
        bookRepository.deleteById(bookOrder);
    }

    @Transactional
    public void updateBook(Integer bookOrder, String title, String author){
        Book book = bookRepository.findById(bookOrder)
                .orElseThrow(()-> new IllegalStateException("Book with order " + bookOrder + " does not extist"));
        if(title != null && title.length() > 0 && !Objects.equals(book.getTitle(), title)){book.setTitle(title);}
        if(author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)){book.setAuthor(author);}

    }
}
