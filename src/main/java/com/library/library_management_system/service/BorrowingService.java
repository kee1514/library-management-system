package com.library.library_management_system.service;

import com.library.library_management_system.entity.Book;
import com.library.library_management_system.entity.Borrowing;
import com.library.library_management_system.entity.User;
import com.library.library_management_system.repository.BookRepository;
import com.library.library_management_system.repository.BorrowingRepository;
import com.library.library_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import com.library.library_management_system.exception.BookAlreadyBorrowedException;
@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowingService(
            BorrowingRepository borrowingRepository,
            UserRepository userRepository,
            BookRepository bookRepository) {

        this.borrowingRepository = borrowingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Borrowing borrowBook(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with id: " + bookId));

        // Check whether the book is already borrowed
        boolean alreadyBorrowed =
                borrowingRepository.existsByBookIdAndStatus(bookId, "BORROWED");

        if (alreadyBorrowed) {
            throw new BookAlreadyBorrowedException(
                    "Book is already borrowed with id: " + bookId);
        }

        Borrowing borrowing = new Borrowing();

        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());
        borrowing.setDueDate(LocalDate.now().plusDays(7));
        borrowing.setStatus("BORROWED");

        return borrowingRepository.save(borrowing);
    }

    public Borrowing returnBook(Long borrowingId) {

        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Borrowing not found with id: " + borrowingId));

        LocalDate returnDate = LocalDate.now();

        borrowing.setReturnDate(returnDate);
        borrowing.setStatus("RETURNED");

        // Calculate fine for late return
        if (returnDate.isAfter(borrowing.getDueDate())) {

            long lateDays =
                    java.time.temporal.ChronoUnit.DAYS.between(
                            borrowing.getDueDate(),
                            returnDate);

            double fine = lateDays * 10;

            borrowing.setFine(fine);

        } else {
            borrowing.setFine(0);
        }

        return borrowingRepository.save(borrowing);
    }
}