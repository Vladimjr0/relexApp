package com.vladimir.relexApp.controllers;

import com.vladimir.relexApp.dtos.RatingRequestDto;
import com.vladimir.relexApp.services.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(
            summary = "Выставление оценок сотрудникам",
            description = "Уровень доступа OWNER"
    )
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("{id}")
    public ResponseEntity<?> ratingForUser(@PathVariable Long id, @RequestBody RatingRequestDto ratingRequestDto){
        return ResponseEntity.ok(ratingService.setUserRating(id,ratingRequestDto));
    }

}
