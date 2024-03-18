package com.vladimir.relexApp.service;

import com.vladimir.relexApp.entity.ChangeList;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExcelService {

    public byte[] generateExcelReport(List<ChangeList> changeLists) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Статистика производства товара");


        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ФИО");
        headerRow.createCell(1).setCellValue("Название товара");
        headerRow.createCell(2).setCellValue("Дата записи");
        headerRow.createCell(3).setCellValue("Количество товара");

        int rowNum = 1;
        int totalQuantity = 0;
        for (ChangeList changeList : changeLists) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(changeList.getUser().getUsername());
            row.createCell(1).setCellValue(changeList.getProduct().getProductName());
            row.createCell(2).setCellValue(changeList.getCreatedAt().toString());
            row.createCell(3).setCellValue(changeList.getQuantity());
            totalQuantity += changeList.getQuantity();
        }


        Row totalRow = sheet.createRow(rowNum++);
        totalRow.createCell(0).setCellValue("Всего произведено товара");
        totalRow.createCell(3).setCellValue(totalQuantity);

        Row dateRow = sheet.createRow(rowNum++);
        dateRow.createCell(0).setCellValue("Дата создания отчета");
        dateRow.createCell(3).setCellValue(LocalDate.now().toString());

        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();


        return outputStream.toByteArray();

    }

}

