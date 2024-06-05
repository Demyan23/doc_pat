package com.doclibrary.service;

import com.doclibrary.dto.ReaderDTO;
import com.doclibrary.dto.assembler.ReaderAssembler;
import com.doclibrary.repository.ReaderRepository;
import com.doclibrary.domain.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public List<ReaderDTO> findAll() {
        return readerRepository.findAll().stream()
                .map(ReaderAssembler::toDto)
                .collect(Collectors.toList());
    }

    public ReaderDTO findById(Long id) {
        return readerRepository.findById(id)
                .map(ReaderAssembler::toDto)
                .orElse(null);
    }

    public ReaderDTO save(ReaderDTO readerDTO) {
        Reader reader = ReaderAssembler.toEntity(readerDTO);
        return ReaderAssembler.toDto(readerRepository.save(reader));
    }

    public void deleteById(Long id) {
        readerRepository.deleteById(id);
    }

    public Float calculateDiscount(Long readerId) {
        Reader reader = readerRepository.findById(readerId).orElse(null);
        if (reader != null) {
            // Припустимо, що категорія "VIP" дає знижку 20%
            return switch (reader.getCategory()) {
                case "VIP" -> 0.20f; // 20% знижка
                case "Student" -> 0.10f; // 10% знижка для студентів
                case "Senior" -> 0.15f; // 15% знижка для пенсіонерів
                default -> 0f; // Без знижки
            };
        }
        return null;
    }
}
