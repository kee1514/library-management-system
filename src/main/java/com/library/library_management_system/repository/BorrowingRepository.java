package com.library.library_management_system.repository;

import com.library.library_management_system.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    boolean existsByBookIdAndStatus(Long bookId, String status);
}