package com.greedymonkeys.category.service;

import com.greedymonkeys.category.model.Category;

import org.springframework.stereotype.Component;

import com.greedymonkeys.category.exceptions.InvalidCategoryException;

@Component
public class CategoryServiceValidation {
    public void validate(Category category) {
        if (category.getName().isBlank()) {
            throw new InvalidCategoryException("The category name cannot be blank");
        }

        if (category.getName() == null) {
            throw new InvalidCategoryException("The category name cannot be null");
        }
    }
}
