package com.example.library.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Book {

    private Long bookId;

    @NotBlank(message = "Book name is required")
    @Size(min = 2, max = 100, message = "Book name must be 2 to 100 characters")
    private String bookName;

    @NotBlank(message = "Author name is required")
    @Size(min = 2, max = 60, message = "Author name must be 2 to 60 characters")
    private String author;

    @Positive(message = "Price must be positive")
    @Max(value = 100000, message = "Price cannot be more than 100000")
    private double price;

    private boolean available;

    public Book() {}

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
