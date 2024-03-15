package com.vladimir.relexApp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "products")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getProducedQuantity() {
        return producedQuantity;
    }

    public void setProducedQuantity(Integer producedQuantity) {
        this.producedQuantity = producedQuantity;
    }

    public String getMeasurementSystem() {
        return measurementSystem;
    }

    public void setMeasurementSystem(String measurementSystem) {
        this.measurementSystem = measurementSystem;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(LocalDate lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }
}
