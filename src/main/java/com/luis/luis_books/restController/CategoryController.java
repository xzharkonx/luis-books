package com.luis.luis_books.restController;

import com.luis.luis_books.model.Category;
import com.luis.luis_books.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@AllArgsConstructor
@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor // Para colocar constructores en aquellos que son rqueridos.
public class CategoryController {

    // Este Objeto es un Bean porque tiene su implementación tiene anotaciones
    // de estereotipo @Service, por lo que ya esta mapeada por spring.
    // Se declara como final porque se cambio la anotacion de @AllArgsConstructor
    // a @RequiredArgsConstructor para evitar que haga el constructor de text (no es bean).
    private final CategoryService categoryService;

    // Este es un Objeto de tipo String, pero no lleva ni implementa clases con
    // ninguna anotación de estereotipo, por eso esque no es tratado como un bean.
    private String text;

    // Con @AllArgsConstructor se agregan todos los contructores y no es
    // necesario agregar este.
    /*
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    */

    /*
    @GetMapping
    public Category getCategory(){
        //return new Category(1,"El principito.");
        return categoryService.validAndReturn(0);
    }
    */

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategorys(){
        List<Category> listCategorys = categoryService.findAll();
        return ResponseEntity.ok(listCategorys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Integer id){
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        Category newCategory = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category){
        Category updatedCategory = categoryService.update(id,category);
        return ResponseEntity.ok(updatedCategory);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
