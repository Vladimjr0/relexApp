package com.vladimir.relexApp.repositories;

import com.vladimir.relexApp.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByUserIdAndRatingDateBetween(Long id, LocalDate startMonth, LocalDate endMonth);
}
