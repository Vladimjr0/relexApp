package com.vladimir.relexApp.controller;

import com.vladimir.relexApp.dto.RatingRequestDto;
import com.vladimir.relexApp.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер для выставления оценок сотрудникам")
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
