package org.example.ad.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ColumnDefault("0")
    private int amount;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Tag tag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
