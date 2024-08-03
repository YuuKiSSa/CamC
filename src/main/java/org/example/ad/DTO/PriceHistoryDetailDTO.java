package org.example.ad.DTO;

import java.time.LocalDate;

public class PriceHistoryDetailDTO {
    private LocalDate date;  
    private double price;

    // Getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
