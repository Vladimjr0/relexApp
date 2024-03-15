package com.vladimir.relexApp.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Builder
public class ProductResponseDto {

    private Long id;

    private String productName;

    private Integer productQuantity;

    private Integer producedQuantity;

    private String measurementSystem;

    private LocalDate lastChangeDate;


    public void setId(Long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProducedQuantity(Integer producedQuantity) {
        this.producedQuantity = producedQuantity;
    }

    public void setMeasurementSystem(String measurementSystem) {
        this.measurementSystem = measurementSystem;
    }

    public void setLastChangeDate(LocalDate lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }
}
