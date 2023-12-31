package com.example.demo.book;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private BookService underTest;

    @BeforeEach
    void setUp() {underTest = new BookService(bookRepository);}

    @Test
    void getAllBooks() {
        underTest.getAllBooks();
        verify(bookRepository).findAll();
    }

    @Test
    void canAddNewBook() {
        Book book = new Book(
                10.00,
                "titanic",
                "jeffrey",
                LocalDate.of(2000, Month.JANUARY,5)
        );
        underTest.addNewBook(book);

        //capture argument pass to method call during test which this captures from book class
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);

        //check if specific call was made on mocked repository and capture agruements that was saved
        //if method of repository was called we capture the arguements passed
        verify(bookRepository).save(bookArgumentCaptor.capture());

        //obtain the values passed
        Book capturedBook = bookArgumentCaptor.getValue();

        assertThat(capturedBook).isEqualTo(book);
    }
    @Test
    void deleteBook() {
        Integer bookOrderToDelete = 1;

        //sets up the behavior of exists by id in the repository mock which should return true
        when(bookRepository.existsById(bookOrderToDelete)).thenReturn(true);
        underTest.deleteBook(bookOrderToDelete);
        verify(bookRepository).deleteById(bookOrderToDelete);
    }
    @Test
    void updateBook() {
        Book mockBook = new Book(
                10.00,
                "Titanic",
                "James Cameron",
                LocalDate.of(2000, Month.JANUARY,5)
        );
        String newTitle = "Art of War";
        String newAuthor = "Sun Tzu";
        Integer bookOrder = 1;

        // Stub the findById method of the mock bookRepository
        when(bookRepository.findById(bookOrder)).thenReturn(Optional.of(mockBook));

        // Call the method to be tested
        underTest.updateBook(bookOrder, newTitle, newAuthor);

        // Verify that the bookRepository's findById method was called with the correct argument
        verify(bookRepository).findById(bookOrder);

        // Verify that the book's title and author were updated correctly
        assertEquals(newTitle, mockBook.getTitle());
        assertEquals(newAuthor, mockBook.getAuthor());
    }
}