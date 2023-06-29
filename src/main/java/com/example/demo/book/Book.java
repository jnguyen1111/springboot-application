package com.example.demo.book;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity //mark class as entity represents a table in database
@Table // specify detail of database table associated with entity allows definition of table name and schema and attributes related with table
public class Book {

    //generates primary key value name= name of generator , sequenceName = name of database sequence,
    // allocation size determines number of values to be allocated from sequence
    //allows primary key value to be automatically fetch next value from sequence
    @SequenceGenerator(
            name =  "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    //strategy for generating primary key values for entity class
    //strategy parameter indicated generation is based on a database sequence which is from sequence name
    //generator parameter is the name of generator to be used for generating primary key values
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    //marks primary key of entity class
    @Id
    private Integer bookOrder;
    private Double price;
    private String title;
    private String author;
    private LocalDate releaseDate;

    public Book() {}
    public Book(
                Double price,
                String title,
                String author,
                LocalDate releaseDate) {
        this.price = price;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public Integer getBookOrder() {return bookOrder;}
    public void setBookOrder(Integer bookOrder) {this.bookOrder = bookOrder;}
    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public LocalDate getReleaseDate() {return releaseDate;}
    public void setReleaseDate(LocalDate releaseDate) {this.releaseDate = releaseDate;}

    @Override
    public String toString() {
        return "book{" +
                "bookNumber=" + bookOrder +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
