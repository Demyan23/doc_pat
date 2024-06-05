package com.doclibrary.repository;

import com.doclibrary.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Procedure(name = "Library.generateBookReport")
    void generateBookReport();

    @Procedure(name = "Library.generateIssuedBooksReport")
    void generateIssuedBooksReport();

    @Procedure(name = "Library.generateFinancialReport")
    void generateFinancialReport();
}
