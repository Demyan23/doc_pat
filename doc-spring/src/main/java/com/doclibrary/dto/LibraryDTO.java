package com.doclibrary.dto;

import java.util.List;

public class LibraryDTO {
    private Long id;
    private List<BookDTO> books;
    private List<ReaderDTO> readers;
    private List<TransactionDTO> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public List<ReaderDTO> getReaders() {
        return readers;
    }

    public void setReaders(List<ReaderDTO> readers) {
        this.readers = readers;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
