package com.greedymonkeys.category.service;

import com.greedymonkeys.category.model.Category;
import com.greedymonkeys.category.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryServiceValidation categoryServiceValidation;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryServiceValidation categoryServiceValidation) {
        this.categoryRepository = categoryRepository;
        this.categoryServiceValidation = categoryServiceValidation;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category create(Category category) {
        category.setId(null);
        categoryServiceValidation.validate(category);
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
