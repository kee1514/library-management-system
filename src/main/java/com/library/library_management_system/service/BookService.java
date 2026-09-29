package com.library.library_management_system.service;
import com.library.library_management_system.dto.BookRequest;
import com.library.library_management_system.dto.BookResponse;
import com.library.library_management_system.entity.Book;
import com.library.library_management_system.exception.BookNotFoundException;
import com.library.library_management_system.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.library.library_management_system.entity.Category;
import com.library.library_management_system.repository.CategoryRepository;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    public BookService(BookRepository bookRepository,
                       CategoryRepository categoryRepository) {

        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public BookResponse addBook(BookRequest request) {

        Book book = new Book();

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found with id: " + request.getCategoryId()));

        book.setCategory(category);

        Book savedBook = bookRepository.save(book);

        BookResponse response = new BookResponse();

        response.setId(savedBook.getId());
        response.setTitle(savedBook.getTitle());
        response.setAuthor(savedBook.getAuthor());
        response.setPrice(savedBook.getPrice());
        if (book.getCategory() != null) {
            response.setCategoryId(book.getCategory().getId());
        }
        return response;
    }

    public List<BookResponse> getAllBooks() {

        List<Book> books = bookRepository.findAll();

        return books.stream().map(book -> {

            BookResponse response = new BookResponse();

            response.setId(book.getId());
            response.setTitle(book.getTitle());
            response.setAuthor(book.getAuthor());
            response.setPrice(book.getPrice());
            if (book.getCategory() != null) {
                response.setCategoryId(book.getCategory().getId());
            }

            return response;

        }).toList();
    }
    public BookResponse getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        BookResponse response = new BookResponse();

        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPrice(book.getPrice());
        response.setCategoryId(book.getCategory().getId());
        return response;
    }
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id: " + id);
        }

        bookRepository.deleteById(id);
    }
    public BookResponse updateBook(Long id, BookRequest request) {

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(request.getTitle());
        existingBook.setAuthor(request.getAuthor());
        existingBook.setPrice(request.getPrice());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new  BookNotFoundException("Category not found with id: " + request.getCategoryId()));

        existingBook.setCategory(category);

        Book updatedBook = bookRepository.save(existingBook);

        BookResponse response = new BookResponse();

        response.setId(updatedBook.getId());
        response.setTitle(updatedBook.getTitle());
        response.setAuthor(updatedBook.getAuthor());
        response.setPrice(updatedBook.getPrice());
        response.setCategoryId(updatedBook.getCategory().getId());

        return response;
    }
    public List<BookResponse> searchBooksByTitle(String title) {

        List<Book> books = bookRepository.findByTitleContaining(title);

        return books.stream().map(book -> {

            BookResponse response = new BookResponse();

            response.setId(book.getId());
            response.setTitle(book.getTitle());
            response.setAuthor(book.getAuthor());
            response.setPrice(book.getPrice());

            if (book.getCategory() != null) {
                response.setCategoryId(book.getCategory().getId());
            }

            return response;

        }).toList();
    }
}