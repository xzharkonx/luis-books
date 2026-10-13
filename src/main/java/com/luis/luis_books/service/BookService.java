package com.luis.luis_books.service;

import com.luis.luis_books.model.Book;

import java.util.List;

public interface BookService {
    //Book validAndReturn(int id);
    Book save(Book book);
    Book update(Integer id, Book book);
    List<Book> findAll();
    Book findById(Integer id);
    void delete(Integer id);

}
