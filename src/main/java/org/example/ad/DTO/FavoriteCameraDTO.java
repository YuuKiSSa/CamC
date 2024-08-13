package org.example.ad.DTO;

import org.example.ad.model.Brand;

public class FavoriteCameraDTO {
    private long id;
    private Brand brand;
    private String model;
    private double idealPrice;
    private String imageUrl;
    private double latestPrice;


    public FavoriteCameraDTO(long id, Brand brand, String model, double idealPrice, String imageUrl, double latestPrice) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.idealPrice = idealPrice;
        this.imageUrl = imageUrl;
        this.latestPrice = latestPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getIdealPrice() {
        return idealPrice;
    }

    public void setIdealPrice(double idealPrice) {
        this.idealPrice = idealPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }
}
