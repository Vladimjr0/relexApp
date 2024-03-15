package com.vladimir.relexApp.dtos;

public class ProductRequestDto {

    private String productName;

    private Integer productQuantity;

    private String measurementSystem;

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

    public String getMeasurementSystem() {
        return measurementSystem;
    }

    public void setMeasurementSystem(String measurementSystem) {
        this.measurementSystem = measurementSystem;
    }
}
