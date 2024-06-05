package com.doclibrary.dto.assembler;

import com.doclibrary.domain.Library;
import com.doclibrary.dto.LibraryDTO;
import java.util.stream.Collectors;

public class LibraryAssembler {
    public static LibraryDTO toDTO(Library library) {
        LibraryDTO dto = new LibraryDTO();
        dto.setId(library.getId());
        dto.setBooks(library.getBooks().stream().map(BookAssembler::toDTO).collect(Collectors.toList()));
        dto.setReaders(library.getReaders().stream().map(ReaderAssembler::toDto).collect(Collectors.toList()));
        dto.setTransactions(library.getTransactions().stream().map(TransactionAssembler::toDto).collect(Collectors.toList()));
        return dto;
    }

    public static Library toEntity(LibraryDTO dto) {
        Library library = new Library();
        library.setId(dto.getId());
        library.setBooks(dto.getBooks().stream().map(BookAssembler::toEntity).collect(Collectors.toList()));
        library.setReaders(dto.getReaders().stream().map(ReaderAssembler::toEntity).collect(Collectors.toList()));
        library.setTransactions(dto.getTransactions().stream().map(TransactionAssembler::toEntity).collect(Collectors.toList()));
        return library;
    }
}
