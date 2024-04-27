package com.kadzys.VBooks.controllers;

import com.kadzys.VBooks.models.Book;
import com.kadzys.VBooks.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (!bookService.exists(book.getIsbn())) {
            return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(path = "/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        Optional<Book> foundBook = bookService.findOne(isbn);
        return foundBook.map(book -> {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        if (!bookService.exists(isbn)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.update(isbn, book), HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> deleteBook(@PathVariable String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
