package com.vladimir.relexApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO productName -> name
    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private Integer producedQuantity;

    @Column(nullable = false)
    private String measurementSystem;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(nullable = false)
    private LocalDate lastChangeDate;


}
