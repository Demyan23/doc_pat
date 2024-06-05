package com.doclibrary.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Book> books;

    @OneToMany
    private List<Reader> readers;

    @OneToMany
    private List<Transaction> transactions;

    public void generateBookReport() {}

    public void generateIssuedBooksReport() {}

    public void generateFinancialReport() {}
}
