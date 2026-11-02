package com.example.library.service;

import com.example.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private long nextId = 1;

    public Book addBook(Book book) {
        book.setBookId(nextId++);
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public List<Book> searchBooksByName(String name) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookName().toLowerCase().contains(name.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public Book updateBook(Long id, Book newBook) {
        Book book = searchBookById(id);
        if (book != null) {
            book.setBookName(newBook.getBookName());
            book.setAuthor(newBook.getAuthor());
            book.setPrice(newBook.getPrice());
            book.setAvailable(newBook.isAvailable());
        }
        return book;
    }

    public Book searchBookById(Long id) {
        for (Book book : books) {
            if (book.getBookId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public Book updateAvailability(Long id, boolean available) {
        Book book = searchBookById(id);
        if (book != null) {
            book.setAvailable(available);
        }
        return book;
    }

    public boolean deleteBook(Long id) {
        Book book = searchBookById(id);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
    }
}
