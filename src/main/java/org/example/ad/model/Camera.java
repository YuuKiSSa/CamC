package org.example.ad.model;

import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String model;
    private String category;
    private String description;
    private Date releaseTime;
    private double effectivePixel;
    private int ISO;
    private int focusPoint;
    private int continuousShot;
    private int videoResolution;
    private int videoRate;

    @ManyToMany(mappedBy = "cameras")
    private List<Tag> tags;

    @OneToMany(mappedBy = "camera")
    private List<Price> prices;

    @OneToMany(mappedBy = "camera")
    private List<Review> reviews;

    @OneToMany(mappedBy = "camera")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "camera")
    private List<Camera_website> camera_websites;

    @OneToMany(mappedBy = "camera")
    private List<Camera_image> camera_images;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
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

    public int getFocusPoint() {
        return focusPoint;
    }

    public void setFocusPoint(int focusPoint) {
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
}
