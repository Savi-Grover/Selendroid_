package ford.auto.BulkAddVehicles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility1 {

	public static List<Map<String, String>> gettestDataInMap() throws IOException {
		List<Map<String, String>> testDataAllRows=null;
		// object of file input
		
		Map<String,String> testData=null;
		try {
			FileInputStream fileInputStream = new FileInputStream("C:\\Users\\sgrover7\\Desktop\\WorkshopHub_Chevin\\src\\test\\resources\\files\\VehicleData.xlsx");

			Workbook workbook = new XSSFWorkbook(fileInputStream);

			// sheet 0(tab 0) of whole excel considered - otherwise declare more
			Sheet sheet = workbook.getSheetAt(0);

			// for getting number of rows
			int lastRowNumber = sheet.getLastRowNum();
			int LastColNumber=sheet.getRow(0).getLastCellNum();  //this is value of number of cols so assigned like that

			//now since you have rows and col number - we need to add to array list object
			
			List list=new ArrayList();
			
			//take a for loop do that it iterates through column 1to n
			for (int i=0;i<LastColNumber;i++) {
				
				Row row=sheet.getRow(0);
				Cell cell=row.getCell(i);
				String rowHeader=cell.getStringCellValue().trim(); //add all these headers in list above
				list.add(rowHeader);
			}
			testDataAllRows = new ArrayList<Map<String,String>>();
			//Map<String, String> testData = new HashMap<String, String>();
			//iteration through all rows
			
			for (int j=1; j<=lastRowNumber; j++) {  //we started from i=1 because i =0 is header
				Row row=sheet.getRow(j);
				testData=new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
				
				for (int k=0;k<LastColNumber;k++) { //iterating form column 1- n for one row
					Cell cell=row.getCell(k);
					String colvalue=cell.getStringCellValue().trim();
					testData.put((String) list.get(k), colvalue);
				}
				testDataAllRows.add(testData);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return testDataAllRows;

	}

}