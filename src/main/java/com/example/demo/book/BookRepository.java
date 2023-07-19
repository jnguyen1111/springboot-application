package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//responsible for database access
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    //SELECT b FROM Book b WHERE b.title =?1
    Optional<Book> findBookByTitle(String title);
}
