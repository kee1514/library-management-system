package com.library.library_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookRequest {
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    private Long categoryId;

    @NotBlank(message = "Title is required")
    @Size(min = 2, message = "Title must have at least 2 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min = 2, message = "Author must have at least 2 characters")
    private String author;

    @Positive(message = "Price must be greater than 0")
    private double price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}