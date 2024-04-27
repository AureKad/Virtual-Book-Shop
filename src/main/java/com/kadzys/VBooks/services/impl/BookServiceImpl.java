package com.kadzys.VBooks.services.impl;

import com.kadzys.VBooks.exceptions.BookNotFoundException;
import com.kadzys.VBooks.models.Book;
import com.kadzys.VBooks.repositories.BookRepository;
import com.kadzys.VBooks.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Boolean exists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public List<Book> findAll() {
        return StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public Book update(String isbn, Book book) {
        book.setIsbn(isbn);

        return bookRepository.findById(isbn).map(existingBook -> {
            Optional.ofNullable(book.getAuthor()).ifPresent(existingBook::setAuthor);
            Optional.ofNullable(book.getTitle()).ifPresent(existingBook::setTitle);
            Optional.ofNullable(book.getPublisher()).ifPresent(existingBook::setPublisher);
            Optional.ofNullable(book.getImgUrl()).ifPresent(existingBook::setImgUrl);
            Optional.ofNullable(book.getPages()).ifPresent(existingBook::setPages);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new BookNotFoundException("Book with isbn: " + isbn + "not found"));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
