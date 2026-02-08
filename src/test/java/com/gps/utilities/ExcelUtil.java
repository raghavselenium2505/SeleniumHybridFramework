package com.gps.utilities;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import com.gps.base.TestBase;

public class ExcelUtil extends TestBase{

	public String getData(String SheetName, String ColName, String excelName) {
		String returnValue = "";
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\" + excelName);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sh = wb.getSheet(SheetName);
			int rowCount = sh.getLastRowNum();
			DataFormatter formatter = new DataFormatter(); // ✅ Use formatter to avoid type mismatch

			for (int i = 0; i <= rowCount; i++) {
				String val = formatter.formatCellValue(sh.getRow(i).getCell(0)); // updated to use formatter
				if (val.equalsIgnoreCase(ColName)) {
					returnValue = formatter.formatCellValue(sh.getRow(i).getCell(1)); // updated to use formatter
					System.out.println(returnValue);
					break;
				}
			}
			//wb.close();
			fis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnValue;
	}
}
