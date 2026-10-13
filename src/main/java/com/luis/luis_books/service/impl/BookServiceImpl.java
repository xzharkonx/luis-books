package com.luis.luis_books.service.impl;

import com.luis.luis_books.model.Book;
import com.luis.luis_books.model.Category;
import com.luis.luis_books.repository.BookRepo;
import com.luis.luis_books.repository.BookRepoImpl;
import com.luis.luis_books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Para colocar constructores en aquellos que son rqueridos.
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Override
    public Book save(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book update(Integer id, Book book) {
        book.setIdBook(id);
        return bookRepo.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book findById(Integer id) {

        return bookRepo.findById(id).orElse(new Book(999,"NO BOOKS FOUND.",new Category(999,"NO CATEGORY FOUND."),false));
    }

    @Override
    public void delete(Integer id) {
        bookRepo.deleteById(id);
    }

    /*
    public BookServiceImpl(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }
    */
    /*
    @Override
    public Book validAndReturn(int id) {
         return (id > 0) ? bookRepo.getBookById(id) : new Book(0,"NO TITLE",false);
    }
    */
}
