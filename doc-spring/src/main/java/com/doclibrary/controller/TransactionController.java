package com.doclibrary.controller;

import com.doclibrary.dto.TransactionDTO;
import com.doclibrary.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public TransactionDTO findById(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public TransactionDTO save(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.save(transactionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        transactionService.deleteById(id);
    }

    @GetMapping("/fine/{transactionId}/{damage}")
    public Float calculateFine(@PathVariable Long transactionId, @PathVariable Boolean damage) {
        return transactionService.calculateFine(transactionId, damage);
    }
}
