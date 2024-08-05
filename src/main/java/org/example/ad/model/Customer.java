package org.example.ad.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"username"})})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 512, nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "customer")
    private List<Preference> preferences;

    @OneToMany(mappedBy = "customer")
    private List<Review> reviews;

    
 // No-arg constructor
    public Customer() {
    }

    // Constructor for initial required fields only
    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
