package com.kadzys.VBooks.services;

import com.kadzys.VBooks.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book book);

    Boolean exists(String isbn);

    List<Book> findAll();

    Optional<Book> findOne(String isbn);

    Book update(String isbn, Book book);

    void delete(String isbn);
}
