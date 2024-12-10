//Dependency - change the title to deployed version

package ford.auto.BulkAddVehicles;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.ford.auto.reusablecomponents.Browser;

import Utilities.ExcelUtility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BulkAddVehicles_PROD {

	//PRE-REQUIRED - METHOD -getCellData_String =put sheetindex proper'
	//PRE-REQUIRED  - METHOD -getRowCount=put proper SheetName'
	//OPTIONAL - METHOD -ReadWholeVehileFile =put proper sheet Index'
	//PRE-REQUIRED - VARIABLE-DevURL= Need to change while running in Production'
	//PRE-REQUIRED - VARIABLE-devApplicationVersion =Need proper Version'
	
	WebDriver driver;
	String BrowserProperty ="webdriver.chrome.driver";
	String DriverPath ="C:\\Users\\sgrover7\\Desktop\\WorkshopHub_Chevin\\src\\test\\resources\\drivers\\chromedriver.exe";
	//String devApplicationVersion ="FleetWave® - FORD v2";
	String devApplicationVersion ="FleetWave® - FORD v2.100";
	static String excelFilePath = "C:\\Users\\sgrover7\\Desktop\\WorkshopHub_Chevin\\src\\test\\resources\\files\\VehicleData.xlsx";
	static XSSFWorkbook workbook;
	static XSSFSheet Sheet;
	String DevURL="https://www.avterminalservices.ford.com/"; 
	boolean staleElement = true;
	boolean staleElement1 = true;
	String VeType = "GOODS";
	String VeStatus = "ACTIVE";
	//String RegionCode = "REGION 010";
	//String RegionCode ="East";
	String AllocationReason = "New";
	public String locatorType, locatorValue;
	String RecordExistingMessage="It is currently Active on the system Please Correct field: VIN";
	String NewVIN="New VIN found -proceeding to Add";

	@BeforeMethod
	public void beforeMethod() {

		// set path of Chromedriver executable
		System.setProperty(BrowserProperty,DriverPath);
		
		// initialize new WebDriver session
		ChromeOptions opt = new ChromeOptions();
		opt.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new ChromeDriver(opt);

		// Wait
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}
	
	
	@Test(priority = 1)
	public void navigateToDev() throws IOException, AWTException, InvalidFormatException, InterruptedException, IllegalArgumentException
	{
		// public static void main(String args[]) {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		// navigate to the web site
		driver.get(DevURL); // dev URL
		// driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;

		// Resize current window to the set dimension
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS) ;

		// Validate page title
		AssertJUnit.assertEquals(driver.getTitle(), devApplicationVersion); // Change it - when updated deployment happens
		System.out.println("Application launched on Dev"+""+devApplicationVersion);
		
		//Count records
		//System.out.println(""+getRowCount());
		int NumberOfRecords = getRowCount();
		
		//--loop for traversing row by row
		lab1: for (int a = 1; a <= NumberOfRecords; a++) {

			//needed data from excel(int row, int col)-vin,state,city,licence
			String value = getCellData_String(a, 0);
			String stateexcel = getCellData_String(a, 6);
			String cityexcell = getCellData_String(a, 7);
			String LicenceExcel = getCellData_String(a, 1);
			String RegionCode1 = getCellData_String(a, 5);

			//Neutral locator iframe
			driver.switchTo().defaultContent();

			// vehicle screen
			
			
			// driver.get("https://wwwdev.avterminalservices.ford.com/fleetwave2/script/browse.aspx?filename=VEHICLES_FW&hardfilter=BASE_TYPE_FW=%27VEHICLE%27");
			WebElement frame1 = driver.findElement(By.id("navtopLeft"));
			driver.switchTo().frame(frame1);
			//WebElement vehiclemenu = driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/ul/li[6]/a/span"));
			
			//in Prod
			WebElement vehiclemenu = driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/ul/li[5]/a/span"));
			
			vehiclemenu.click();
			driver.switchTo().defaultContent();
			Thread.sleep(8000);

			// switch frame to main screen
			WebElement frame2 = driver.findElement(By.id("main"));
			driver.switchTo().frame(frame2);
			// System.out.println("!");

			// Whole data -optional to run
			// ReadWholeVehileFile();

			// call Vehilcle search
			WebElement SearchVIN = driver.findElement(By.cssSelector("#filtval"));
			//isElementPresent(driver, SearchVIN);
			SearchVIN.click();
			SearchVIN.sendKeys(value);

			// click enter
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("topframe");
			WebElement addButton = driver.findElement(By.cssSelector("#btnadd"));
			addButton.click();
			Thread.sleep(20000);

			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");
			wait.until(ExpectedConditions.elementToBeClickable(By.id("vehicle_id_fw")));

			WebElement FormVIN = driver.findElement(By.id("vehicle_id_fw"));
			FormVIN.sendKeys(value);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(20000);
			
			//Handle unexpected Alert
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert Message: " + alertText);
				alert.accept();
				driver.get(DevURL);
				continue lab1;

			}
			
			catch(NoAlertPresentException e) {
				System.out.println(e.getCause()+""+value);
				//break;
			}

			/*
			 * if
			 * (driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/div[2]/table"
			 * )).isDisplayed()) { //driver.findElement(By.xpath(
			 * "/html/body/table/tbody/tr/td/form/div[2]/table")).click();
			 * System.out.println("Record of VIN already found"); break; }
			 */
			//Red Banner message if already exist
			if (driver.findElement(By.id("error_td")).getText().contains(RecordExistingMessage)) {
				// driver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/div[2]/table")).click();
				System.out.println("Message --------"+value+""+RecordExistingMessage);
				//break; //no need since end of file; or next will pickup
			}

			else {
				System.out.println("Message --------"+value+""+NewVIN);
				FormVIN.clear();
				Thread.sleep(2000);

				// Fill Vehicle Form and save
				FillFormVehicle(value, VeType, VeStatus, RegionCode1, stateexcel, cityexcell);
				Thread.sleep(12000);
				
				//check for expected alert and if got any - cancel saving that record- move to next record
				try {
					Alert alert = driver.switchTo().alert();
					String alertText = alert.getText();
					System.out.println("Alert Message: " + alertText);
					alert.accept();
					driver.get(DevURL);
					continue lab1;

				}
				
				catch(NoAlertPresentException e) {
					System.out.println(e.getCause()+""+value);
					//break;
				}

				// fill Licence Plate from excel
				WebElement LicencePlate = driver.findElement(By.id("license_plate_fw"));
				LicencePlate.click();
				LicencePlate.sendKeys(LicenceExcel);
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

				// Save
				WebElement SaveButton = driver.findElement(By.id("buttonsave"));
				SaveButton.click();
				Thread.sleep(25000);

				// Check for Reallocation Screen
				// driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				WebElement ReallocationTab = driver.findElement(By.xpath("/html/body/form/ul/li[1]/a/span"));
				String test1 = ReallocationTab.getText();
				if (test1.equals("Reallocate Driver")) {

					System.out.println("Addition screen is filled and reached to First Re-allocation Screen");

					// Add Start Date
					WebElement startdatepicker = driver.findElement(By.xpath("/html/body/form/fieldset/table/tbody/tr/td/table/tbody/tr[10]/td[2]/div/i"));
					startdatepicker.click();
					Thread.sleep(1000);

					// click Today
					WebElement today = driver.findElement(By.xpath("/html/body/div[5]/div[2]/button[1]"));
					today.click();
					Thread.sleep(1000);

					// allocation_reason_fw
					//wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("allocation_reason_fw"))));
					//Select ReasonType = new Select(driver.findElement(By.id("allocation_reason_fw")));


					boolean staleElement1 = true;
					while (staleElement1) {

						try {

							// Re-allocation reason choose
							driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
							WebElement Reason = driver.findElement(By.id("allocation_reason_fw"));
							//isElementPresent(driver, Reason);
							// vehicleState.click();
							WebElement ReasonOptions = driver.findElement(By.xpath("/html/body/form/fieldset/table/tbody/tr/td/table/tbody/tr[15]/td[2]/select/option"));
							// Actions action = new Actions(driver);
							Thread.sleep(10000);

							List<WebElement> liItems = Reason.findElements(By.xpath("/html/body/form/fieldset/table/tbody/tr/td/table/tbody/tr[15]/td[2]/select/option"));
							for (WebElement liItem : liItems) {
								//System.out.println(liItem.getText());
								while (liItem.getText().equalsIgnoreCase(AllocationReason)) {
									System.out.println("Found Reason Type..."+AllocationReason);
									liItem.click();
									Thread.sleep(5000);
									// action.moveToElement(liItem).click();
									Thread.sleep(5000);
									break;

								}
							}
							staleElement1 = false;
						}

						catch(StaleElementReferenceException e){
							staleElement1 = true;
						}}

					//ReasonType.selectByVisibleText(AllocationReason);
					//driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

					// success save
					WebElement success = driver.findElement(By.xpath("/html/body/form/fieldset/div/input[1]"));
					success.click();
					System.out.println("Save Clicked On Reallocation Screen");
					Thread.sleep(10000);

					// second -reallocation screen empty, need to click cancel
					WebElement ReallocationTab2 = driver.findElement(By.xpath("/html/body/form/ul/li[1]/a/span"));
					String test2 = ReallocationTab2.getText();
					if (test2.equals("Reallocate Driver")) {
						System.out.println("Successfully Saved - Second time Re-allocation screen received");
						
						//Click Cancel
						WebElement cancel = driver.findElement(By.xpath("/html/body/form/fieldset/div/input[2]"));
						cancel.click();
						Thread.sleep(15000);

						//VerifySearchVIN(value);
						/////////
						try {
							//Always call from main HomePage
							//keeping iframe at neutral locator
							driver.switchTo().defaultContent();

							WebElement frame = driver.findElement(By.id("navtopLeft"));
							driver.switchTo().frame(frame1);
							//WebElement vehiclemenu1 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/ul/li[6]/a/span"));
							//in prod
							WebElement vehiclemenu1 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/ul/li[5]/a/span"));
							
							vehiclemenu1.click();
							driver.switchTo().defaultContent();
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

							// switch frame to main screen
							WebElement frame22 = driver.findElement(By.id("main"));
							driver.switchTo().frame(frame22);

							WebElement SearchVIN2 = driver.findElement(By.cssSelector("#filtval"));
							SearchVIN2.click();
							SearchVIN2.clear();
							Thread.sleep(5000);

							// search
							SearchVIN2.sendKeys(value);
							//Robot robot= new Robot();
							robot.keyPress(KeyEvent.VK_ENTER);
							robot.keyRelease(KeyEvent.VK_ENTER);

							//driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
							Thread.sleep(5000);
							WebElement roww1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form[2]/div[1]/table/tbody/tr/td[2]/div/a"));
							String extractresult = roww1.getText();

							if (extractresult.contains(value)) {
								driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
								System.out.println(" VIN added is Verified");
								
							} else {
								driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
								System.out.println(" VIN added NOT Verified");
							}
						}

						catch(Exception e) {
							System.out.println(e.getCause());
						}


					}

					else {
						System.out.println("Something went wrong - dint reach to Second Reallocation Screen");
						break;

					}


				} else {
					System.out.println("Something went wrong - dint reach to First Reallocation Screen");
				}

			}

		}

	}
	
	@AfterTest
	public void CloseBrowser() {
		//driver.close();
		//driver.quit();
	}
	
	//Verify Search Function - NOT USED - have to use commands inside main Function
	public void VerifySearchVIN(String vin) throws InterruptedException {

		try {
			//Always call from main HomePage
			//keeping iframe at neutral locator
			driver.switchTo().defaultContent();

			WebElement frame1 = driver.findElement(By.id("navtopLeft"));
			driver.switchTo().frame(frame1);
			//WebElement vehiclemenu = driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/ul/li[6]/a/span"));
			//in prod
			WebElement vehiclemenu = driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/ul/li[5]/a/span"));
			
			vehiclemenu.click();
			driver.switchTo().defaultContent();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			// switch frame to main screen
			WebElement frame2 = driver.findElement(By.id("main"));
			driver.switchTo().frame(frame2);

			WebElement SearchVIN2 = driver.findElement(By.cssSelector("#filtval"));
			SearchVIN2.click();
			SearchVIN2.clear();
			Thread.sleep(5000);

			// search
			SearchVIN2.sendKeys(vin);
			Robot robot= new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			//driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			Thread.sleep(5000);
			WebElement roww1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form[2]/div[1]/table/tbody/tr/td[2]/div/a"));
			String extractresult = roww1.getText();

			if (extractresult.contains(vin)) {
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				System.out.println(" Vin added is Found");
				//break;
			} else {
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				System.out.println(" Vin NOT Found");
			}
		}

		catch(Exception e) {
			System.out.println(e.getCause());
		}


	}
	//for the purpose of region extracting from sheet
	//public void FillFormVehicle(String VinValue, String VeType, String VeStatus, String RegionCode1, String State,
			//String City) throws AWTException, InterruptedException
	
	public void FillFormVehicle(String VinValue, String VeType, String VeStatus, String RegionCode, String State,
			String City) throws AWTException, InterruptedException {
		WebDriverWait waitt = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		try {
			// VIn number
			// WebElement
			// textbox=waitt.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("vehicle_id_fw"))));
			WebElement FormVIN = driver.findElement(By.id("vehicle_id_fw"));
			Thread.sleep(5000);
			//FormVIN.clear();
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			FormVIN.sendKeys(VinValue);
			Thread.sleep(5000);
			// driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

			// Choose Vehicle Type
			waitt.until(
					ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("vehicle_type_fw"))));
			Select vehicleType = new Select(driver.findElement(By.id("vehicle_type_fw")));
			vehicleType.selectByVisibleText(VeType);
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

			// Choose Vehicle Status
			waitt.until(ExpectedConditions
					.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("vehicle_status_fw"))));
			Select vehicleStatus = new Select(driver.findElement(By.id("vehicle_status_fw")));
			vehicleStatus.selectByVisibleText(VeStatus);
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

			
			// Choose Region Code
			/*waitt.until(
					ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("company_code_fw"))));
			Select RegionCode11 = new Select(driver.findElement(By.id("company_code_fw")));
			RegionCode11.selectByVisibleText(RegionCode);*/
			

			boolean staleElement = true;
			while (staleElement) {

				try {
					//Comment above region if region choosen from sheet
					
					WebElement RegCode = driver.findElement(By.id("company_code_fw"));
					WebElement Regoptions = driver.findElement(By.xpath(
							"/html/body/table/tbody/tr/td/form/fieldset/div[1]/table/tbody/tr[5]/td[2]/div/select/option"));
					// Actions action = new Actions(driver);
					Thread.sleep(20000);
					List<WebElement> liregCodes= RegCode.findElements(By.xpath("/html/body/table/tbody/tr/td/form/fieldset/div[1]/table/tbody/tr[5]/td[2]/div/select/option")); 
					for (WebElement liItem : liregCodes) {
						System.out.println(liItem.getText());
						while (liItem.getText().equalsIgnoreCase(RegionCode)) {
							System.out.println("Found Region...");
							liItem.click();
							Thread.sleep(5000);
							// action.moveToElement(liItem).click();
							Thread.sleep(5000);
							break;
						}
					}
					
					
					// State choose
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					WebElement vehicleState = driver.findElement(By.id("department_fw"));
					//isElementPresent(driver, vehicleState);
					// vehicleState.click();
					WebElement stateoptions = driver.findElement(By.xpath(
							"/html/body/table/tbody/tr/td/form/fieldset/div[1]/table/tbody/tr[5]/td[4]/div/select/option"));
					// Actions action = new Actions(driver);
					Thread.sleep(20000);

					List<WebElement> liItems = vehicleState.findElements(By.xpath(
							"/html/body/table/tbody/tr/td/form/fieldset/div[1]/table/tbody/tr[5]/td[4]/div/select/option"));
					for (WebElement liItem : liItems) {
						System.out.println(liItem.getText());
						while (liItem.getText().equalsIgnoreCase(State)) {
							System.out.println("Found State...");
							liItem.click();
							Thread.sleep(5000);
							// action.moveToElement(liItem).click();
							Thread.sleep(5000);
							break;
						}
					}

					// City choose
					driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
					WebElement vehicleCity = driver.findElement(By.id("costcentre_fw"));
					//isElementPresent(driver, vehicleCity);
					// vehicleCity.click();
					WebElement stateoptions1 = driver.findElement(By.xpath(
							"/html/body/table/tbody/tr/td/form/fieldset/div[1]/table/tbody/tr[5]/td[6]/div/select/option"));
					// Actions action1 = new Actions(driver);

					List<WebElement> liItems1 = vehicleCity.findElements(By.xpath(
							"/html/body/table/tbody/tr/td/form/fieldset/div[1]/table/tbody/tr[5]/td[6]/div/select/option"));
					for (WebElement liItem : liItems1) {
						System.out.println(liItem.getText());
						while (liItem.getText().equalsIgnoreCase(City)) {
							System.out.println("Found City...");
							liItem.click();
							Thread.sleep(5000);
							// action1.moveToElement(liItem).click();
							break;
						}
					}

					staleElement = false;

				} catch (StaleElementReferenceException e) {

					staleElement = true;

				}

			}

			// Get Vehicle Info
			WebElement GetVehicleInfo = driver.findElement(By.id("fordgetinfofromintelligence"));
			GetVehicleInfo.click();
			Thread.sleep(5000);
			
		/*	try {
				// Switching to Alert        
		        Alert alert = driver.switchTo().alert();		
		        		
		        // Capturing alert message.    
		        String alertMessage= driver.switchTo().alert().getText();		
		        		
		        // Displaying alert message		
		        System.out.println(alertMessage);	
		        Thread.sleep(5000);
		        		
		        // Accepting alert		
		        alert.accept();
		        
		        //break;
		        return;

			} catch (NoAlertPresentException e) {
				e.printStackTrace();

			}*/
		//}
			

		}
		catch (UnhandledAlertException f) {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				System.out.println("Alert data: " + alertText);
				alert.accept();

			} catch (NoAlertPresentException e) {
				e.printStackTrace();

			}
		}

	}

	public static WebElement findElement(WebDriver driver, By selector, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(selector));
		return findElement(driver, selector, timeOutInSeconds);
	}

	//Function to Read Whole Sheet Data - NOT USED
	public void ReadWholeVehileFile() throws IOException {
		String excelFilePath = "C:\\Users\\sgrover7\\Desktop\\WorkshopHub_Chevin\\src\\test\\resources\\files\\VehicleData.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0); // sheet tab at 0 index
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue());
					break;
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					break;
				}
				System.out.print(" - ");
			}
			System.out.println();
		}

		workbook.close();
		inputStream.close();
	}

	
	//Function to fetch row count -USED
	public static int getRowCount() throws IOException, InvalidFormatException {
		try {
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet Sheet = workbook.getSheet("Sheet3"); //name

			int RowCount = Sheet.getLastRowNum();
			System.out.println("Total Records to add---"+RowCount);
			return RowCount;
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			// printStackTrace(e);
		}
		return 0;
	}
	
	//Function to Fetch Excel Data from cell ( data in String but accepted for all types-formatter class) - USED
	public static String getCellData_String(int rowNum, int colNum) {
		String datacell = null;
		try {

			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet Sheet = workbook.getSheetAt(1);

			DataFormatter formatter = new DataFormatter();
			Object data = formatter.formatCellValue(Sheet.getRow(rowNum).getCell(colNum));
			datacell = data.toString().trim();
			// System.out.println("Cell Data---"+data);

		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			// printStackTrace(e);
		}
		return datacell;

	}
	
	//Function to Fetch Excel Data from cell ( data in number ) - NOT USED
	public static void getCellData_Number(int rowNum, int colNum) {
		try {

			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet Sheet = workbook.getSheetAt(1);

			double Celldata = Sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println("Cell Data---" + Celldata);
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			// printStackTrace(e);
		}

	}

	//Function to Detect if Element is Present - NOT USED
	public static boolean isElementPresent(WebDriver driver, WebElement Obj) {
		boolean result = false;
		try {
			if (Obj.isDisplayed()) {
				System.out.println("WEBELEMENT  FOUND------" + Obj);
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
