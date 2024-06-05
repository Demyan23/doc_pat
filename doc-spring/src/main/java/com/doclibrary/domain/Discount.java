package com.doclibrary.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private Float percentage;
}
