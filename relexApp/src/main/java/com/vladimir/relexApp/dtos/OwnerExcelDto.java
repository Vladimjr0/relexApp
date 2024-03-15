package com.vladimir.relexApp.dtos;

import lombok.Data;

import java.time.Month;

@Data
public class OwnerExcelDto {
    private String email;
    private Integer year;
    private Integer month;
    private Integer day;
}
