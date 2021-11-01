package com.greedymonkeys.category.http;

import com.greedymonkeys.category.exceptions.InvalidCategoryException;
import com.greedymonkeys.category.model.Category;
import com.greedymonkeys.category.model.CategoryDTO;
import com.greedymonkeys.category.service.CategoryService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired private CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    public Category findById(@PathVariable Long id) {
        return categoryService
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, "Category not found"));
    }

    @PostMapping
    public Category create(@RequestBody CategoryDTO categoryDTO) {
        var category = Category.fromDTO(categoryDTO);
        try {
            return categoryService.create(category);
        } catch (InvalidCategoryException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
