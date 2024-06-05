package com.doclibrary.repository;

import com.doclibrary.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Procedure(name = "Discount.applyDiscount")
    Float applyDiscount(Float amount);
}
