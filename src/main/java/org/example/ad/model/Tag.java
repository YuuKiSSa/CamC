package org.example.ad.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;

    @ManyToMany
    private List<Camera> cameras;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
