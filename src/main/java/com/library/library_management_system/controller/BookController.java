package com.library.library_management_system.controller;

import com.library.library_management_system.entity.Book;
import com.library.library_management_system.service.BookService;
import com.library.library_management_system.dto.BookRequest;
import com.library.library_management_system.dto.BookResponse;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE BOOK
    @PostMapping
    public BookResponse addBook(@Valid @RequestBody BookRequest request) {
        return bookService.addBook(request);
    }

    // GET ALL BOOKS
    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET BOOK BY ID
    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // UPDATE BOOK
    @PutMapping("/{id}")
    public BookResponse updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {
        return bookService.updateBook(id, request);
    }

    // DELETE BOOK
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
    @GetMapping("/search")
    public List<BookResponse> searchBooks(@RequestParam String title) {
        return bookService.searchBooksByTitle(title);
    }
}