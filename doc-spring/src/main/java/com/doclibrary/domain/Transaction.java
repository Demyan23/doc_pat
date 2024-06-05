package com.doclibrary.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Reader reader;

    private LocalDate issueDate;
    private LocalDate expectedReturnDate;
    private LocalDate returnedDate;
}
