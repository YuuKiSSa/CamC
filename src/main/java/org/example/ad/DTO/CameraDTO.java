package org.example.ad.DTO;
import org.example.ad.model.Brand;
import org.example.ad.model.Camera;
import org.example.ad.model.Category;

import java.time.LocalDate;

public class CameraDTO {
    private long id;
    private Brand brand;
    private String model;
    private Category category;
    private String description;
    private LocalDate releaseTime;
    private double initialPrice;
    private double effectivePixel;
    private int ISO;
    private Integer focusPoint;
    private int continuousShot;
    private int videoResolution;
    private int videoRate;
    private String imageUrl;

    public CameraDTO(Camera camera, String url){
        id = camera.getId();
        brand = camera.getBrand();
        model = camera.getModel();
        category = camera.getCategory();
        description = camera.getDescription();
        releaseTime = camera.getReleaseTime();
        initialPrice = camera.getInitialPrice();
        effectivePixel = camera.getEffectivePixel();
        ISO = camera.getISO();
        focusPoint = camera.getFocusPoint();
        continuousShot = camera.getContinuousShot();
        videoRate = camera.getVideoRate();
        videoResolution = camera.getVideoResolution();
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDate releaseTime) {
        this.releaseTime = releaseTime;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getEffectivePixel() {
        return effectivePixel;
    }

    public void setEffectivePixel(double effectivePixel) {
        this.effectivePixel = effectivePixel;
    }

    public int getISO() {
        return ISO;
    }

    public void setISO(int ISO) {
        this.ISO = ISO;
    }

    public Integer getFocusPoint() {
        return focusPoint;
    }

    public void setFocusPoint(Integer focusPoint) {
        this.focusPoint = focusPoint;
    }

    public int getContinuousShot() {
        return continuousShot;
    }

    public void setContinuousShot(int continuousShot) {
        this.continuousShot = continuousShot;
    }

    public int getVideoResolution() {
        return videoResolution;
    }

    public void setVideoResolution(int videoResolution) {
        this.videoResolution = videoResolution;
    }

    public int getVideoRate() {
        return videoRate;
    }

    public void setVideoRate(int videoRate) {
        this.videoRate = videoRate;
    }

    public String getUrl() {
        return imageUrl;
    }

    public void setUrl(String url) {
        this.imageUrl = url;
    }
}
