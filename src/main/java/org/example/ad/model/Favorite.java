package org.example.ad.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"camera_id", "customer_id"})})
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ColumnDefault("0")
    private double idealPrice;

    @ManyToOne
    private Camera camera;

    @ManyToOne
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getIdealPrice() {
        return idealPrice;
    }

    public void setIdealPrice(double idealPrice) {
        this.idealPrice = idealPrice;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
