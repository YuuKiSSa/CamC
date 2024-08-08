package org.example.ad.DTO;

import java.time.LocalDate;
import java.util.List;

public class CameraAddDTO {
    private String brand;
    private String model;
    private String category;
    private String description;
    private LocalDate releaseTime;
    private double initialPrice;
    private double effectivePixel;
    private int iso;
    private Integer focusPoint;
    private int continuousShot;
    private int videoResolution;
    private int videoRate;
    private List<String> imageUrls; 
    private List<CameraWebsiteDTO> websites; 

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
        return iso;
    }

    public void setISO(int iso) {
        this.iso = iso;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<CameraWebsiteDTO> getWebsites() {
        return websites;
    }

    public void setWebsites(List<CameraWebsiteDTO> websites) {
        this.websites = websites;
    }
}
