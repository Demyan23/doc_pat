package com.doclibrary.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String genre;
    private Float rentalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}
