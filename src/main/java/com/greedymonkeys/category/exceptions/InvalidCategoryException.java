package com.greedymonkeys.category.exceptions;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String error) {
        super(error);
    }
}
