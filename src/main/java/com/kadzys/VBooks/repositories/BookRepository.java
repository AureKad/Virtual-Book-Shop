package com.kadzys.VBooks.repositories;

import com.kadzys.VBooks.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
