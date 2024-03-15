package com.vladimir.relexApp.repositories;

import com.vladimir.relexApp.entity.ChangeList;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChangeListRepository extends JpaRepository<ChangeList, Long> {
    void deleteByProductId(Long productId);

    List<ChangeList> findAllByUserId(Long id);

    List<ChangeList> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

    List<ChangeList> findByCreatedAt(LocalDate startDate);

    void deleteByUserId(Long userId);

}
