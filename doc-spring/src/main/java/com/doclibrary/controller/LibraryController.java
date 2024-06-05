package com.doclibrary.controller;

import com.doclibrary.dto.LibraryDTO;
import com.doclibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<LibraryDTO> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.getLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDTO> createLibrary(@RequestBody LibraryDTO libraryDTO) {
        return ResponseEntity.ok(libraryService.createLibrary(libraryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Long id, @RequestBody LibraryDTO libraryDTO) {
        return ResponseEntity.ok(libraryService.updateLibrary(id, libraryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report/books")
    public ResponseEntity<Void> generateBookReport() {
        libraryService.generateBookReport();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/report/issued-books")
    public ResponseEntity<Void> generateIssuedBooksReport() {
        libraryService.generateIssuedBooksReport();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/report/financial")
    public ResponseEntity<Void> generateFinancialReport() {
        libraryService.generateFinancialReport();
        return ResponseEntity.ok().build();
    }
}
