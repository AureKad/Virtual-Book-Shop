package com.kadzys.VBooks;

import com.kadzys.VBooks.models.Book;

public final class TestDataUtil {
    private TestDataUtil() {}

    public static Book createBookA() {
        Book book = Book.builder()
                .isbn("123")
                .title("Harry Potter")
                .pages(200)
                .author("J.K. Rowling")
                .publisher("Me")
                .imgUrl("123.png")
                .build();
        return book;
    }
}
