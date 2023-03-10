package com.company.repository;

import com.company.model.Book;
import com.company.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> searchBookByStatus(BookStatus status);
    List<Book> findByTitle(String title);
}
