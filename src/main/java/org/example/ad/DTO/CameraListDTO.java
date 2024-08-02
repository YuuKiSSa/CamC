package org.example.ad.DTO;

import org.example.ad.model.Brand;
import org.example.ad.model.Camera;

public class CameraListDTO {
    private long id;
    private Brand brand;
    private String model;
    private double initialPrice;
    private String imageUrl;

    public CameraListDTO(Camera camera, String url){
        id = camera.getId();
        brand = camera.getBrand();
        model = camera.getModel();
        initialPrice = camera.getInitialPrice();
        imageUrl = url;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
