package com.example.demo.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository underTest;

    @AfterEach
    void tearDown() {underTest.deleteAll();}

    @Test
    void checkFindBookByTitleExist() {
        String bookTitle = "Guitar Techniques";
        Book book = new Book(
            10.00,bookTitle,"bernth",LocalDate.of(2000, Month.JANUARY,5)
        );
        underTest.save(book);
        Optional<Book> bookExists = underTest.findBookByTitle(bookTitle);
        assertTrue(bookExists.isPresent());
        assertEquals(book, bookExists.get());
    }

    @Test
    void checkFindBookByTitleDoesNotExist() {
        String bookTitle = "Guitar Techniques";
        Optional<Book> bookExists = underTest.findBookByTitle(bookTitle);
        assertFalse(bookExists.isPresent());
    }
}