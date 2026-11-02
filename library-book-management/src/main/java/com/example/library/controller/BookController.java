package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    // Example: GET /books/search?name=java
    @GetMapping("/search")
    public List<Book> searchByName(@RequestParam String name) {
        return service.searchBooksByName(name);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        Book updated = service.updateBook(id, book);
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        return updated;
    }

    @GetMapping("/{id}")
    public Book searchBookById(@PathVariable Long id) {
        Book book = service.searchBookById(id);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        return book;
    }

    @PatchMapping("/{id}/availability")
    public Book updateAvailability(@PathVariable Long id,
                                   @RequestBody Map<String, Boolean> request) {
        Boolean available = request.get("available");
        if (available == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "available field is required");
        }
        Book book = service.updateAvailability(id, available);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        return book;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        if (!service.deleteBook(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }
}
