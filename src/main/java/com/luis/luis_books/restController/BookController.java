package com.luis.luis_books.restController;

import com.luis.luis_books.model.Book;
import com.luis.luis_books.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@AllArgsConstructor
@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor // Para colocar constructores en aquellos que son rqueridos.
public class BookController {

    // Este Objeto es un Bean porque tiene su implementación tiene anotaciones
    // de estereotipo @Service, por lo que ya esta mapeada por spring.
    // Se declara como final porque se cambio la anotacion de @AllArgsConstructor
    // a @RequiredArgsConstructor para evitar que haga el constructor de text (no es bean).
    private final BookService bookService;

    // Este es un Objeto de tipo String, pero no lleva ni implementa clases con
    // ninguna anotación de estereotipo, por eso esque no es tratado como un bean.
    private String text;

    // Con @AllArgsConstructor se agregan todos los contructores y no es
    // necesario agregar este.
    /*
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    */

    /*
    @GetMapping
    public Book getBook(){
        //return new Book(1,"El principito.");
        return bookService.validAndReturn(0);
    }
    */

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> listBooks = bookService.findAll();
        return ResponseEntity.ok(listBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Integer id){
        Book book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        Book newBook = bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Integer id, @RequestBody Book book){
        Book updatedBook = bookService.update(id,book);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
