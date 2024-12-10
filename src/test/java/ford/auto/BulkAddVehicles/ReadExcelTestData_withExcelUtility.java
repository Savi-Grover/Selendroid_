package ford.auto.BulkAddVehicles;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import Utilities.ExcelUtility;

public class ReadExcelTestData_withExcelUtility {
	
	/* This is just an example of how data is retrieved from TestData.xlxs */
	
	// just run this class to see if data from xlsx is captured-
	//here get(0) - means row 1
	//and get("header")  - indicates value of which parameter should be used
	
	@Test
	// for getting attributes from row 1 - test case 1 in testData.xlxs
	public void test1() {
		try {
		List<Map<String,String>> testDataInMap= ExcelUtility1.gettestDataInMap();
		
		//you can use this kind of line in place of sendkeys- on different test cases
		System.out.println(testDataInMap.get(0).get("User"));
		System.out.println(testDataInMap.get(0).get("Password"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}