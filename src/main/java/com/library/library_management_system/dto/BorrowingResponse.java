package com.library.library_management_system.dto;

import java.time.LocalDate;

public class BorrowingResponse {

    private Long id;
    private Long userId;
    private String userName;

    private Long bookId;
    private String bookTitle;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private String status;
    private double fine;

    public BorrowingResponse(
            Long id,
            Long userId,
            String userName,
            Long bookId,
            String bookTitle,
            LocalDate borrowDate,
            LocalDate dueDate,
            LocalDate returnDate,
            String status,
            double fine) {

        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.fine = fine;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public double getFine() {
        return fine;
    }
}