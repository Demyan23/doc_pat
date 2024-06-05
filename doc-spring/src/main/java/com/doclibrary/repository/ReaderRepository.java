package com.doclibrary.repository;

import com.doclibrary.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    @Procedure(name = "Reader.calculateDiscount")
    Float calculateDiscount();
}
