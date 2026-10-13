package com.luis.luis_books.repository;

import com.luis.luis_books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Integer> {

    // Book getBookById(int id);
}
