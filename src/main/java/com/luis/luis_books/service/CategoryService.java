package com.luis.luis_books.service;

import com.luis.luis_books.model.Category;

import java.util.List;

public interface CategoryService {
    //Category validAndReturn(int id);
    Category save(Category Category);
    Category update(Integer id, Category Category);
    List<Category> findAll();
    Category findById(Integer id);
    void delete(Integer id);

}
