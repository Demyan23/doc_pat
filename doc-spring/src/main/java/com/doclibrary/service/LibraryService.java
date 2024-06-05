package com.doclibrary.service;

import com.doclibrary.dto.assembler.BookAssembler;
import com.doclibrary.dto.assembler.LibraryAssembler;
import com.doclibrary.domain.Book;
import com.doclibrary.domain.Library;
import com.doclibrary.domain.Transaction;
import com.doclibrary.dto.LibraryDTO;
import com.doclibrary.dto.assembler.ReaderAssembler;
import com.doclibrary.dto.assembler.TransactionAssembler;
import com.doclibrary.repository.BookRepository;
import com.doclibrary.repository.LibraryRepository;
import com.doclibrary.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<LibraryDTO> getAllLibraries() {
        return libraryRepository.findAll().stream()
                .map(LibraryAssembler::toDTO)
                .collect(Collectors.toList());
    }

    public LibraryDTO getLibraryById(Long id) {
        Library library = libraryRepository.findById(id).orElseThrow(() -> new RuntimeException("Library not found"));
        return LibraryAssembler.toDTO(library);
    }

    public LibraryDTO createLibrary(LibraryDTO libraryDTO) {
        Library library = LibraryAssembler.toEntity(libraryDTO);
        library = libraryRepository.save(library);
        return LibraryAssembler.toDTO(library);
    }

    public LibraryDTO updateLibrary(Long id, LibraryDTO libraryDTO) {
        Library existingLibrary = libraryRepository.findById(id).orElseThrow(() -> new RuntimeException("Library not found"));
        existingLibrary.setBooks(libraryDTO.getBooks().stream().map(BookAssembler::toEntity).collect(Collectors.toList()));
        existingLibrary.setReaders(libraryDTO.getReaders().stream().map(ReaderAssembler::toEntity).collect(Collectors.toList()));
        existingLibrary.setTransactions(libraryDTO.getTransactions().stream().map(TransactionAssembler::toEntity).collect(Collectors.toList()));
        existingLibrary = libraryRepository.save(existingLibrary);
        return LibraryAssembler.toDTO(existingLibrary);
    }

    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }

    public void generateBookReport() {
        List<Book> books = bookRepository.findAll();
        books.forEach(book -> {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("Rental Price: " + book.getRentalPrice());
            System.out.println("-----------------------------");
        });
    }

    public void generateIssuedBooksReport() {
        List<Transaction> transactions = transactionRepository.findAll();
        transactions.forEach(transaction -> {
            Book book = transaction.getBook();
            System.out.println("Book Title: " + book.getTitle());
            System.out.println("Reader: " + transaction.getReader().getName());
            System.out.println("Issue Date: " + transaction.getIssueDate());
            System.out.println("Expected Return Date: " + transaction.getExpectedReturnDate());
            if (transaction.getExpectedReturnDate().isBefore(LocalDate.now()) && transaction.getReturnedDate() == null) {
                System.out.println("Status: Overdue");
            } else {
                System.out.println("Status: On Time");
            }
            System.out.println("-----------------------------");
        });
    }

    public void generateFinancialReport() {
        List<Transaction> transactions = transactionRepository.findAll();
        double totalIncome = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getReturnedDate() != null) {
                totalIncome += transaction.getBook().getRentalPrice();
            }
        }
        System.out.println("Total Income: " + totalIncome);
    }
}
