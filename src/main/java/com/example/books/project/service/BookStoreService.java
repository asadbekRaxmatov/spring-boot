package com.example.books.project.service;

import com.example.books.project.domain.Book;
import com.example.books.project.domain.Customer;
import com.example.books.project.domain.Order;
import com.example.books.project.repository.BookRepository;
import com.example.books.project.repository.CustomerRepository;
import com.example.books.project.repository.OrderRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookStoreService {
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public BookStoreService(BookRepository bookrepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.bookRepository = bookrepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    //redis
    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }


    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByTitleAndPrice(String title, Double price) {
        return bookRepository.findByTitleAndPrice(title, price);
    }

    public void deleteByid(Double id) {
        Book book = bookRepository.getOne(id.longValue());
        bookRepository.delete(book);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Order> getOrdersByCustomer(String customerName) {
        // Find a customer by name
        Optional<Customer> optionalCustomer = customerRepository.findByName(customerName);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            return orderRepository.findByCustomer(customer);
        } else {
            return Collections.emptyList();
        }
    }

    public List<Book> getBooksByPriceRange(double minPrice, double maxPrice) {
        return bookRepository.findByPriceBetween(minPrice,maxPrice);

    }
}
