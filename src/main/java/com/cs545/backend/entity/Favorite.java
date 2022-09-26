package com.cs545.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "favorite")
@Data
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Customer owner;

    @ManyToMany
    private List<Property> properties;

    public void setOwner(Customer customer){
        if(customer != null){
            this.owner = customer;
            customer.addFavorite(this);
        }
    }
}