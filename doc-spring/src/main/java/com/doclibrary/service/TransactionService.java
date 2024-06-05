package com.doclibrary.service;

import com.doclibrary.domain.Book;
import com.doclibrary.domain.Reader;
import com.doclibrary.dto.TransactionDTO;
import com.doclibrary.dto.assembler.TransactionAssembler;
import com.doclibrary.repository.BookRepository;
import com.doclibrary.repository.ReaderRepository;
import com.doclibrary.repository.TransactionRepository;
import com.doclibrary.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream()
                .map(TransactionAssembler::toDto)
                .collect(Collectors.toList());
    }

    public TransactionDTO findById(Long id) {
        return transactionRepository.findById(id)
                .map(TransactionAssembler::toDto)
                .orElse(null);
    }

    public TransactionDTO save(TransactionDTO transactionDTO) {
        Transaction transaction = TransactionAssembler.toEntity(transactionDTO);
        transaction.setBook(bookRepository.findById(transactionDTO.getBookId()).orElse(null));
        transaction.setReader(readerRepository.findById(transactionDTO.getReaderId()).orElse(null));
        return TransactionAssembler.toDto(transactionRepository.save(transaction));
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    public Float calculateFine(Long transactionId, Boolean damage) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction != null) {
            float fine = 0f;

            // Розрахунок штрафу за пошкодження книги
            if (damage) {
                // Припустимо, що стандартний штраф за пошкодження книги становить 50% від заставної вартості
                fine += transaction.getBook().getRentalPrice() * 0.50f;
            }

            // Розрахунок штрафу за прострочення повернення книги
            if (transaction.getExpectedReturnDate().isBefore(LocalDate.now()) && transaction.getReturnedDate() == null) {
                long daysOverdue = ChronoUnit.DAYS.between(transaction.getExpectedReturnDate(), LocalDate.now());
                // Припустимо, що штраф за прострочення становить 1 у.о. за кожен день прострочення
                fine += daysOverdue * 1f;
            }

            return fine;
        }
        return 0f; // Без штрафу
    }
}
