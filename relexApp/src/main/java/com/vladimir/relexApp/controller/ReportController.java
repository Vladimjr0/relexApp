package com.vladimir.relexApp.controller;

import com.vladimir.relexApp.dto.OwnerExcelDto;
import com.vladimir.relexApp.entity.ChangeList;
import com.vladimir.relexApp.service.ChangeListService;
import com.vladimir.relexApp.service.ExcelService;
import com.vladimir.relexApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Tag(name = "Контроллер для вывода статистики")
@RestController
@RequestMapping("/report")
public class ReportController {

    private final UserService userService;

    private final ExcelService excelService;

    private final ChangeListService changeListService;


    public ReportController(UserService userService, ExcelService excelService, ChangeListService changeListService) {
        this.userService = userService;
        this.excelService = excelService;
        this.changeListService = changeListService;
    }

    @Operation(
            summary = "вывод статистики по дню, месяцу, пользователю и в целом по ферме с возможностью скачать Excel файл",
            description = "Уровень доступа OWNER"
    )
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping()
    public ResponseEntity<byte[]> generateExcelReport(@RequestBody OwnerExcelDto ownerExcelDto) throws IOException {
        List<ChangeList> changeLists = changeListService.getAllChangeListByDate(userService.getUserId(ownerExcelDto.getEmail()),
                ownerExcelDto.getYear(),
                ownerExcelDto.getMonth(),
                ownerExcelDto.getDay());

        byte[] excelBytes = excelService.generateExcelReport(changeLists);

        String filePath = "C:/Users/parov/Documents/excel_report.xlsx";

        try(FileOutputStream fos = new FileOutputStream(filePath)){
            fos.write(excelBytes);
        }

        return ResponseEntity.ok(excelBytes);

    }

}
