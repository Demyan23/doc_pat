package com.doclibrary.dto.assembler;

import com.doclibrary.domain.Discount;
import com.doclibrary.dto.DiscountDTO;

public class DiscountAssembler {
    public static DiscountDTO toDto(Discount discount) {
        DiscountDTO dto = new DiscountDTO();
        dto.setId(discount.getId());
        dto.setCategory(discount.getCategory());
        dto.setPercentage(discount.getPercentage());
        return dto;
    }

    public static Discount toEntity(DiscountDTO dto) {
        Discount discount = new Discount();
        discount.setId(dto.getId());
        discount.setCategory(dto.getCategory());
        discount.setPercentage(dto.getPercentage());
        return discount;
    }
}
