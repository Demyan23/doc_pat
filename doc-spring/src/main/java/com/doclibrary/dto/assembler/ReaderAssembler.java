package com.doclibrary.dto.assembler;

import com.doclibrary.domain.Reader;
import com.doclibrary.dto.ReaderDTO;
import com.doclibrary.dto.DiscountDTO;

import java.util.stream.Collectors;

public class ReaderAssembler {
    public static ReaderDTO toDto(Reader reader) {
        ReaderDTO dto = new ReaderDTO();
        dto.setId(reader.getId());
        dto.setName(reader.getName());
        dto.setAddress(reader.getAddress());
        dto.setPhone(reader.getPhone());
        dto.setCategory(reader.getCategory());
        dto.setDiscounts(reader.getDiscounts().stream()
                .map(DiscountAssembler::toDto)
                .collect(Collectors.toList()));
        return dto;
    }

    public static Reader toEntity(ReaderDTO dto) {
        Reader reader = new Reader();
        reader.setId(dto.getId());
        reader.setName(dto.getName());
        reader.setAddress(dto.getAddress());
        reader.setPhone(dto.getPhone());
        reader.setCategory(dto.getCategory());
        reader.setDiscounts(dto.getDiscounts().stream()
                .map(DiscountAssembler::toEntity)
                .collect(Collectors.toList()));
        return reader;
    }
}
