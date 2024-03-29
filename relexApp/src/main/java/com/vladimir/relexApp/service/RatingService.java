package com.vladimir.relexApp.service;

import com.vladimir.relexApp.mapper.ApiMapper;
import com.vladimir.relexApp.dto.RatingRequestDto;
import com.vladimir.relexApp.dto.UserResponseDto;
import com.vladimir.relexApp.entity.Rating;
import com.vladimir.relexApp.entity.User;
import com.vladimir.relexApp.repository.RatingRepository;
import com.vladimir.relexApp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDto setUserRating(Long id, RatingRequestDto ratingRequestDto){

        Rating rating = new Rating();
        User user = userRepository.findById(id).get();
        rating.setUser(user);
        rating.setRatingValue(ratingRequestDto.getRating());
        rating.setRatingDate(LocalDate.now());
        ratingRepository.save(rating);

        YearMonth currentMonth = YearMonth.now();

        List<Rating> ratingForMonth = ratingRepository.findByUserIdAndRatingDateBetween(
                id,
                currentMonth.atDay(1),
                currentMonth.atEndOfMonth()
        );

        Integer averageRating = (int) ratingForMonth.stream()
                .mapToInt(Rating::getRatingValue)
                .average()
                .orElse(0);
        user.setAvrRating(averageRating);
        userRepository.save(user);


        return ApiMapper.INSTANCE.userToUserResponseDto(user);

    }


}
