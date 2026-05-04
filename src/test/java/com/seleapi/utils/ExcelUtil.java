package com.seleapi.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    private Workbook workbook;

    // ================= CONSTRUCTOR =================

    public ExcelUtil(String filePath) {

        try {
            FileInputStream fis = new FileInputStream(filePath);

            if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else {
                throw new RuntimeException("Unsupported Excel format: " + filePath);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load Excel file: " + filePath, e);
        }
    }

    // ================= DATAPROVIDER METHOD =================

    public Object[][] getSheetDataByTestCaseName(
            String sheetName,
            String testCaseName) {

        try {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            DataFormatter formatter = new DataFormatter();
            List<Object[]> dataList = new ArrayList<>();

            for (int i = 1; i <= rowCount; i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                String currentTestName =
                        formatter.formatCellValue(row.getCell(0));

                if (currentTestName.equalsIgnoreCase(testCaseName)) {

                    Object[] rowData = new Object[colCount - 1];

                    for (int j = 1; j < colCount; j++) {

                        Cell cell = row.getCell(j);
                        rowData[j - 1] =
                                formatter.formatCellValue(cell);
                    }

                    dataList.add(rowData);
                }
            }

            if (dataList.isEmpty()) {
                throw new RuntimeException(
                        "No data found for test case: " + testCaseName);
            }

            return dataList.toArray(new Object[0][]);

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel data", e);
        }
    }

    // ================= OPTIONAL: SINGLE VALUE FETCH =================

    public String getSingleCellValue(
            String sheetName,
            String keyName) {

        try {

            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();

            for (Row row : sheet) {

                if (formatter.formatCellValue(row.getCell(0))
                        .equalsIgnoreCase(keyName)) {

                    return formatter.formatCellValue(row.getCell(1));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "";
    }

    // ================= CLOSE WORKBOOK =================

    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}