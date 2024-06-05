package com.doclibrary.controller;

import com.doclibrary.dto.DiscountDTO;
import com.doclibrary.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping
    public List<DiscountDTO> findAll() {
        return discountService.findAll();
    }

    @GetMapping("/{id}")
    public DiscountDTO findById(@PathVariable Long id) {
        return discountService.findById(id);
    }

    @PostMapping
    public DiscountDTO save(@RequestBody DiscountDTO discountDTO) {
        return discountService.save(discountDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        discountService.deleteById(id);
    }

    @GetMapping("/apply/{discountId}/{amount}")
    public Float applyDiscount(@PathVariable Long discountId, @PathVariable Float amount) {
        return discountService.applyDiscount(discountId, amount);
    }
}
