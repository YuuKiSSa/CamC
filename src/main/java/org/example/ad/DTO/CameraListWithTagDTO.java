package org.example.ad.DTO;

import org.example.ad.model.Brand;
import org.example.ad.model.Camera;

import java.util.List;

public class CameraListWithTagDTO {
    private long id;
    private Brand brand;
    private String model;
    private double initialPrice;
    private double latestPrice;
    private String imageUrl;
    private List<String> tags;

    public CameraListWithTagDTO(Camera camera, String url, List<String> tags) {
        this.id = camera.getId();
        this.brand = camera.getBrand();
        this.model = camera.getModel();
        this.initialPrice = camera.getInitialPrice();
        this.imageUrl = url;
        this.tags = tags;
    }

    // Getters and setters
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
