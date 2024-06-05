package com.doclibrary.controller;

import com.doclibrary.dto.ReaderDTO;
import com.doclibrary.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping
    public List<ReaderDTO> findAll() {
        return readerService.findAll();
    }

    @GetMapping("/{id}")
    public ReaderDTO findById(@PathVariable Long id) {
        return readerService.findById(id);
    }

    @PostMapping
    public ReaderDTO save(@RequestBody ReaderDTO readerDTO) {
        return readerService.save(readerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        readerService.deleteById(id);
    }

    @GetMapping("/discount/{readerId}")
    public Float calculateDiscount(@PathVariable Long readerId) {
        return readerService.calculateDiscount(readerId);
    }
}
