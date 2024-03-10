package com.example.books.project.web.rest;


import com.example.books.project.domain.Book;
import com.example.books.project.domain.Order;
import com.example.books.project.service.BookStoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class BookStoreController {
    private final BookStoreService bookStoreService;

    @GetMapping("/books")
    public String getall() {
        return "hello asadbek";
    }


    @Autowired
    public BookStoreController (BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }



    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookStoreService.getBookById(id);
    }

    @GetMapping("/byGenre")
    public ResponseEntity<List<Book>> getBookByGenre(@RequestParam String genre) {
        List<Book> books = bookStoreService.getBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/byAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author) {
        List<Book> books = bookStoreService.getBooksByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/ordersByCustomer")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@RequestParam String customerName) {
        List<Order> orders = bookStoreService.getOrdersByCustomer(customerName);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/byPriceRange")
    public ResponseEntity<List<Book>> getBooksByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Book> books = bookStoreService.getBooksByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @PostMapping("/book")
    public ResponseEntity create(@RequestBody Book book) {
        log.debug("Creating new book from {}", book);

        Book book1 = bookStoreService.save(book);
        return ResponseEntity.ok(book1);
    }

    @PostMapping("/books")
    public ResponseEntity getAll() {
        log.debug("Get a list of books");

        List<Book> bookList = bookStoreService.findAll();
        return ResponseEntity.ok(bookList);
    }

    @PostMapping("/book/{title}")
    public ResponseEntity getAll(@PathVariable String title) {
        log.debug("Get a list of books by", title);

        List<Book> bookList = bookStoreService.findByTitle(title);
        return ResponseEntity.ok(bookList);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteById(@PathVariable Double id) {
        bookStoreService.deleteByid(id);
        return ResponseEntity.ok("The selected book has been deleted");
    }

}
