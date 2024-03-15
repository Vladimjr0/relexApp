package com.vladimir.relexApp.services;

import com.vladimir.relexApp.entity.ChangeList;
import com.vladimir.relexApp.repositories.ChangeListRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ChangeListService {

    private final ChangeListRepository changeListRepository;

    public ChangeListService(ChangeListRepository changeListRepository) {
        this.changeListRepository = changeListRepository;
    }

    public List<ChangeList> getAllChangeListByDate(Long id, Integer year, Integer month, Integer day) {
        if (year == null && month == null && day == null && id != null) {
            return changeListRepository.findAllByUserId(id);
        }
        if (year != null && month != null && day != null) {
            LocalDate startOfMonth = LocalDate.of(year, month, day);
            return changeListRepository.findByCreatedAt(startOfMonth);
        }
        if (year != null && month != null) {
            LocalDate startOfMonth = LocalDate.of(year, month, 1);
            LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());
            return changeListRepository.findByCreatedAtBetween(startOfMonth, endOfMonth);
        } else {
            return changeListRepository.findAll();
        }
    }
}


