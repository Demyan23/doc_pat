package com.doclibrary.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String category;

    @OneToMany
    private List<Discount> discounts;
}
