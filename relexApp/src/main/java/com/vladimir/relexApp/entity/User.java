package com.vladimir.relexApp.entity;

import jakarta.persistence.*;
import lombok.Data;


//TODO liquabase/flyway - автоматические миграции в базу
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //TODO UUID - type
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private Integer avrRating;

}
