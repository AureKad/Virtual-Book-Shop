package com.kadzys.VBooks.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
public class Book {
    @Id
    @Column(nullable = false, updatable = true)
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String imgUrl;
    private Integer pages;
}
