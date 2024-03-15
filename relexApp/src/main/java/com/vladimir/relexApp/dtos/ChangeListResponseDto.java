package com.vladimir.relexApp.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ChangeListResponseDto {

    private String email;

    private String productName;

    private Integer producedQuantity;

    private LocalDate lastChangeDate;


}
