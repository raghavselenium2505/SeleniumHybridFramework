package com.gps.utilities;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import com.gps.base.TestBase;

public class ExcelUtil extends TestBase {

    // ---------------------------------------------
    // OLD METHOD (DO NOT REMOVE)
    // ---------------------------------------------
    public String getData(String SheetName,
                          String ColName,
                          String excelName) {

        String returnValue = "";

        try {

            FileInputStream fis =
                    new FileInputStream(
                            System.getProperty("user.dir")
                                    + "\\src\\test\\resources\\excel\\"
                                    + excelName);

            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sh = wb.getSheet(SheetName);

            int rowCount = sh.getLastRowNum();
            DataFormatter formatter =
                    new DataFormatter();

            for (int i = 0; i <= rowCount; i++) {

                String val =
                        formatter.formatCellValue(
                                sh.getRow(i).getCell(0));

                if (val.equalsIgnoreCase(ColName)) {

                    returnValue =
                            formatter.formatCellValue(
                                    sh.getRow(i).getCell(1));
                    break;
                }
            }

            wb.close();
            fis.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return returnValue;
    }

    // ---------------------------------------------
    // NEW METHOD FOR DATAPROVIDER
    // ---------------------------------------------
    public Object[][] getSheetDataByTestCaseName(
            String sheetName,
            String testCaseName,
            String excelName) {

        try {

            FileInputStream fis =
                    new FileInputStream(
                            System.getProperty("user.dir")
                                    + "\\src\\test\\resources\\excel\\"
                                    + excelName);

            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sh = wb.getSheet(sheetName);

            int rowCount = sh.getLastRowNum();
            int colCount =
                    sh.getRow(0).getLastCellNum();

            DataFormatter formatter =
                    new DataFormatter();

            for (int i = 1; i <= rowCount; i++) {

                String currentTestName =
                        formatter.formatCellValue(
                                sh.getRow(i).getCell(0));

                if (currentTestName
                        .equalsIgnoreCase(testCaseName)) {

                    Object[][] data =
                            new Object[1][colCount - 1];

                    for (int j = 1;
                         j < colCount;
                         j++) {

                        data[0][j - 1] =
                                formatter.formatCellValue(
                                        sh.getRow(i).getCell(j));
                    }

                    wb.close();
                    fis.close();

                    return data;
                }
            }

            wb.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException(
                "Test case not found in Excel: "
                        + testCaseName);
    }
}