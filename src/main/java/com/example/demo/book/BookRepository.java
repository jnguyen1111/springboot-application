package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//responsible for database access
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
