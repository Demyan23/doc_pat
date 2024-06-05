package com.doclibrary.dto.assembler;

import com.doclibrary.domain.Book;
import com.doclibrary.dto.BookDTO;

public class BookAssembler {
    public static BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setGenre(book.getGenre());
        dto.setRentalPrice(book.getRentalPrice());
        return dto;
    }

    public static Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setRentalPrice(dto.getRentalPrice());
        return book;
    }
}