package com.example.books.project.repository;

import com.example.books.project.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByTitle(String title);

    List<Book> findByTitleAndPrice(String title, Double price);

    List<Book> findByGenre(String genre);

    List<Book> findByAuthor(String author);

    List<Book> findByPriceBetween(double minPrice, double maxPrice);

}
