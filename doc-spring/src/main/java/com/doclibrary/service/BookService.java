package com.doclibrary.service;

import com.doclibrary.dto.assembler.BookAssembler;
import com.doclibrary.domain.Book;
import com.doclibrary.dto.BookDTO;
import com.doclibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookAssembler::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return BookAssembler.toDTO(book);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = BookAssembler.toEntity(bookDTO);
        book = bookRepository.save(book);
        return BookAssembler.toDTO(book);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setRentalPrice(bookDTO.getRentalPrice());
        existingBook = bookRepository.save(existingBook);
        return BookAssembler.toDTO(existingBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Float calculateRentalPrice(Long bookId, int days) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        return book.getRentalPrice() * days;
    }
}
