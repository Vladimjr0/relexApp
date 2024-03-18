package com.vladimir.relexApp.service;

import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class ScheduledService {
    private final ExcelService excelService;

    private final EmailService emailService;

    private final ChangeListService changeListService;

    public ScheduledService(ExcelService excelService, EmailService emailService, ChangeListService changeListService) {
        this.excelService = excelService;
        this.emailService = emailService;
        this.changeListService = changeListService;
    }

    @Scheduled(cron = "0 00 18 * * ?")
    public void sendExcelEmail() throws IOException, MessagingException {
        String to = "vladimjrshevcov@gmail.com";
        String subject = "Статистика за сегодня";
        String text = "Вложение: статистика в формате Excel";
        String attachmentName = "statistics.xlsx";

        byte[] excelBytes = excelService.generateExcelReport(changeListService.getAllChangeListByDate(null, LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));
        emailService.sendEmailWithAttachment(to,subject,text,excelBytes,attachmentName);
    }
}
