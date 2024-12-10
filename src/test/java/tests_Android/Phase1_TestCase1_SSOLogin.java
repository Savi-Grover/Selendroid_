package tests_Android;

import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Phase1_TestCase1_SSOLogin {

	// Pre-requisite - 1. Remove mobile browser history / cache.
	// Pre-requisite - 2. clean ADFS saved passwords saved in mobile browser.
	// Pre-requisite - 3. Maintain CDSID user hierarchy in terminal and technician record in proper Ford env (dev/qa)
	//otherwise testcase will fail
	// Pre-requisite - 4. Start Appium server before running

	@Test
	//Basic Authentication
	public void LoginTech() throws InterruptedException, AWTException {


		//Inheriting Base Class
		BaseClass abc =new BaseClass();
		
		//Declare Test Data in sheet
		String clientname=BaseClass.getCellData_String(1, 1);
		String email=BaseClass.getCellData_String(1, 2);
		String username=BaseClass.getCellData_String(1, 3);
		String passwd=BaseClass.getCellData_String(1, 4);
		
		//Run setup and Login
		abc.setup();
		abc.ValidateLoginParams(clientname,email,username,passwd);

	}
}
