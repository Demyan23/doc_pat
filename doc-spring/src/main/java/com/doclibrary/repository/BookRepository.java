package com.doclibrary.repository;

import com.doclibrary.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Procedure(name = "Book.calculateRentalPrice")
    Float calculateRentalPrice(int duration);
}
