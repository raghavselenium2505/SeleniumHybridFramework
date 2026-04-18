package com.gps.utilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.gps.base.TestBase;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DynamicDataProvider extends TestBase {

	// ================= MAIN DATAPROVIDER =================

	@DataProvider(name = "dynamicData")
	public Object[][] getData(Method method) throws Exception {

		Test testAnnotation = method.getAnnotation(Test.class);
		String[] groups = testAnnotation.groups();

		for (String group : groups) {

			if (group.equalsIgnoreCase("json")) {
				return getJsonData(method.getName());
			}

			if (group.equalsIgnoreCase("excel")) {
				return getExcelData(method.getName());
			}
		}

		throw new RuntimeException("No valid group defined for test: " + method.getName());
	}

	// ================= JSON =================

	private Object[][] getJsonData(String testName) throws Exception {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/excel/testdata.json";

		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

		List<Object[]> dataList = new ArrayList<>();

		for (Object obj : jsonArray) {

			JSONObject jsonObject = (JSONObject) obj;

			String testCase = jsonObject.get("testCase").toString();

			if (testCase.equalsIgnoreCase(testName)) {

				dataList.add(
						new Object[] { jsonObject.get("username").toString(), jsonObject.get("password").toString() });
			}
		}

		return dataList.toArray(new Object[0][]);
	}
	// ================= EXCEL =================

	private Object[][] getExcelData(String testName) {

		String filePath = System.getProperty("user.dir") + "/src/test/resources/excel/Gps_Rules.xls";

		ExcelUtil excelUtil = new ExcelUtil(filePath);

		Object[][] data = excelUtil.getSheetDataByTestCaseName("Signin", testName);

		if (data.length == 0) {

			throw new RuntimeException("No Excel data found for: " + testName);
		}

		return data;
	}
}