package com.cs545.backend.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "property")
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int numberOfRooms;
    @Column(nullable = false)
    private int size;
    @Column(nullable = false)
    private int numberOfBathrooms;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private int views;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @OneToOne
    private Address address;
    @ElementCollection
    @CollectionTable(name = "photos", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "photos")
    private List<String> photos;
    @ManyToOne
    private Owner owner;
    @OneToMany(mappedBy = "property")
    private List<Request> requests;

    public void setUser(Owner owner){
        if(owner != null){
            this.owner = owner;
            if(!owner.getOwnedProperties().contains(this)){
                owner.addProperty(this);
            }
        }
    }

    public void addRequest(Request request) {
        if(request != null){
            this.requests.add(request);
            if(!this.equals(request.getProperty())){
                request.setProperty(this);
            }
        }
    }
}