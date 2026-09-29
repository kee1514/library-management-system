package com.library.library_management_system.controller;

import com.library.library_management_system.dto.BorrowingResponse;
import com.library.library_management_system.entity.Borrowing;
import com.library.library_management_system.service.BorrowingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping
    public BorrowingResponse borrowBook(
            @RequestParam Long userId,
            @RequestParam Long bookId) {

        Borrowing borrowing =
                borrowingService.borrowBook(userId, bookId);

        return convertToResponse(borrowing);
    }

    @PostMapping("/{id}/return")
    public BorrowingResponse returnBook(
            @PathVariable Long id) {

        Borrowing borrowing =
                borrowingService.returnBook(id);

        return convertToResponse(borrowing);
    }

    private BorrowingResponse convertToResponse(Borrowing borrowing) {

        return new BorrowingResponse(
                borrowing.getId(),
                borrowing.getUser().getId(),
                borrowing.getUser().getName(),
                borrowing.getBook().getId(),
                borrowing.getBook().getTitle(),
                borrowing.getBorrowDate(),
                borrowing.getDueDate(),
                borrowing.getReturnDate(),
                borrowing.getStatus(),
                borrowing.getFine()
        );
    }
}