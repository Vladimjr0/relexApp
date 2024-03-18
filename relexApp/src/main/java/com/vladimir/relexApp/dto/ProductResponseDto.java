package com.vladimir.relexApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class ProductResponseDto {

    private Long id;

    private String productName;

    private Integer productQuantity;

    private Integer producedQuantity;

    private String measurementSystem;

    private LocalDate lastChangeDate;

}
