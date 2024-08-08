package org.example.ad.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "brand", "model" }) })
public class Camera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	private Brand brand;

	@Column(length = 30, nullable = false)
	private String model;

	@Enumerated(EnumType.STRING)
	private Category category;

	@Column(length = 500)
	private String description;

	@Column(nullable = false)
	private LocalDate releaseTime;

	private double initialPrice;
	private double effectivePixel;
	private int ISO;
	private Integer focusPoint;
	private int continuousShot;
	private int videoResolution;
	private int videoRate;

	@ManyToMany(mappedBy = "cameras", cascade = CascadeType.ALL)
	private List<Tag> tags;

	@OneToMany(mappedBy = "camera", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Price> prices;

	@OneToMany(mappedBy = "camera", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;

	@OneToMany(mappedBy = "camera", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "camera", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CameraWebsite> cameraWebsites;

	@OneToMany(mappedBy = "camera", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CameraImage> cameraImages;

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

	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public LocalDate getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(LocalDate releaseTime) {
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<CameraImage> getCameraImages() {
		return cameraImages;
	}

	public void setCameraImages(List<CameraImage> cameraImages) {
		this.cameraImages = cameraImages;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<CameraWebsite> getCameraWebsites() {
		return cameraWebsites;
	}

	public void setCameraWebsites(List<CameraWebsite> cameraWebsites) {
		this.cameraWebsites = cameraWebsites;
	}
}
