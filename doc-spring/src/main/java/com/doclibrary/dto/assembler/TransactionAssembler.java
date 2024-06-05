package com.doclibrary.dto.assembler;

import com.doclibrary.domain.Transaction;
import com.doclibrary.dto.TransactionDTO;

public class TransactionAssembler {
    public static TransactionDTO toDto(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setBookId(transaction.getBook().getId());
        dto.setReaderId(transaction.getReader().getId());
        dto.setIssueDate(transaction.getIssueDate());
        dto.setExpectedReturnDate(transaction.getExpectedReturnDate());
        dto.setReturnedDate(transaction.getReturnedDate());
        return dto;
    }

    public static Transaction toEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        // Assuming Book and Reader entities will be fetched from database and set here
        transaction.setIssueDate(dto.getIssueDate());
        transaction.setExpectedReturnDate(dto.getExpectedReturnDate());
        transaction.setReturnedDate(dto.getReturnedDate());
        return transaction;
    }
}
