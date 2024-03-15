package com.vladimir.relexApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;

    private Integer avrRating;


}
