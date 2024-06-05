package com.doclibrary.service;

import com.doclibrary.dto.DiscountDTO;
import com.doclibrary.dto.assembler.DiscountAssembler;
import com.doclibrary.repository.DiscountRepository;
import com.doclibrary.domain.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public List<DiscountDTO> findAll() {
        return discountRepository.findAll().stream()
                .map(DiscountAssembler::toDto)
                .collect(Collectors.toList());
    }

    public DiscountDTO findById(Long id) {
        return discountRepository.findById(id)
                .map(DiscountAssembler::toDto)
                .orElse(null);
    }

    public DiscountDTO save(DiscountDTO discountDTO) {
        Discount discount = DiscountAssembler.toEntity(discountDTO);
        return DiscountAssembler.toDto(discountRepository.save(discount));
    }

    public void deleteById(Long id) {
        discountRepository.deleteById(id);
    }

    public Float applyDiscount(Long discountId, Float amount) {
        Discount discount = discountRepository.findById(discountId).orElse(null);
        if (discount != null) {
            return amount * (1 - discount.getPercentage() / 100);
        }
        return null;
    }
}
