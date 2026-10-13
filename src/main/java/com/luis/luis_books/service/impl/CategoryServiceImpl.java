package com.luis.luis_books.service.impl;

import com.luis.luis_books.model.Category;
import com.luis.luis_books.repository.CategoryRepo;
import com.luis.luis_books.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Para colocar constructores en aquellos que son rqueridos.
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category update(Integer id, Category category) {
        category.setIdCategory(id);
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Integer id) {

        return categoryRepo.findById(id).orElse(new Category(999,"NO CATEGORY FOUND."));
    }

    @Override
    public void delete(Integer id) {
        categoryRepo.deleteById(id);
    }

    /*
    public CategoryServiceImpl(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }
    */
    /*
    @Override
    public Category validAndReturn(int id) {
         return (id > 0) ? categoryRepo.getCategoryById(id) : new Category(0,"NO TITLE",false);
    }
    */
}
