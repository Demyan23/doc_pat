package com.doclibrary.dto;

import java.util.List;

public class ReaderDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String category;
    private List<DiscountDTO> discounts;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<DiscountDTO> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountDTO> discounts) {
        this.discounts = discounts;
    }
}
