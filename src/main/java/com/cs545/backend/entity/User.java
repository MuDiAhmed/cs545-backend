package com.cs545.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Data
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
}