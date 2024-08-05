package org.example.ad.DTO;

import org.example.ad.model.Brand;
import org.example.ad.model.Camera;

public class CameraListDTO {
    private long id;
    private Brand brand;
    private String model;
    private double initialPrice; 
    private double latestPrice;   
    private String imageUrl;
    private double averageRate;

    public CameraListDTO(Camera camera, String url){
        this.id = camera.getId();
        this.brand = camera.getBrand();
        this.model = camera.getModel();
        this.initialPrice = camera.getInitialPrice();
        this.imageUrl = url;
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

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }
}
