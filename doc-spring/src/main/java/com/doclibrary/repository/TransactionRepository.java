package com.doclibrary.repository;

import com.doclibrary.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Procedure(name = "Transaction.calculateFine")
    Float calculateFine(Boolean damage);
}
