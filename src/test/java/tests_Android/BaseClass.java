package tests_Android;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;

// for keeping all capabilities related data - CANNOT run this class if it  has no @test
public class BaseClass {

	public static AppiumDriver driver; // declare at class level

	@BeforeClass
	public AppiumDriver<MobileElement> setup() {

		try {

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "moto g5");
			caps.setCapability(MobileCapabilityType.UDID, "ZY224VM3HD"); // taken from adb devices

			// both are same below - check both classes
			// caps.setCapability(CapabilityType.PLATFORM_NAME,"Android");
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");

			// caps.setCapability(MobileCapabilityType.APP,""); //path of apk in resorces

			// took from apk info app - playstore
			// always written for native apps
			caps.setCapability("appPackage", "com.chevin.workshophub");
			caps.setCapability("appActivity", "com.chevin.workshophub.MainActivity");

			// passing appium host URL
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			// driver = new AppiumDriver<MobileElement>(url,caps); //appiumdriver is parent
			// class to both android; ios
			// assigning appium url and capabilities to driver
			driver = new AndroidDriver<MobileElement>(url, caps); // extends above - but some actions specific to
																	// android operation
			// driver = new IOSDriver<MobileElement>(url,caps); //extends above - but some
			// actions specific to ios operation
			// driver = new WindowsDriver(url,caps);
			System.out.println("App Started");

		}

		catch (Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
		return driver; // returning driver so that it can be consumed by other classes

	}

	// old method for basic login in app - not used after sso updation
	@Test(priority = 100)
	void LoginTest() {

		LoginBasicAuth_OLD("FORDDEV", "sgrover7", "Ford1234");

	}

	// Declaration of re-usable Login method with Parameters
	// old method for basic login in app - not used after sso updation
	public AppiumDriver<MobileElement> LoginBasicAuth_OLD(String client1, String user1, String pass1) {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement client = driver.findElement(By.xpath(
				"//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.widget.EditText"));
		// WebElement client =
		// driver.findElement(By.xpath("//android.view.View[@content-desc=\"LOG
		// IN\"]"));
		client.click();
		client.sendKeys(client1);

		// click on next
		// WebElement next =
		// driver.findElement(By.xpath("//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View/android.widget.Button"));
		// next.click();
		System.out.println("Client Authenticate Successful");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement user = driver.findElement(By.id("username"));
		user.click();
		user.sendKeys(user1);

		WebElement pass = driver.findElement(By.id("password"));
		pass.click();
		pass.sendKeys(pass1);
		driver.hideKeyboard();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement Login = driver.findElement(By.xpath(
				"//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.view.View[4]/android.widget.Button"));
		Login.click();
		System.out.println("Technician Login Successful");
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		return driver; // returning driver so that it can be consumed by other classes

	}

	// Declaration of re-usable Login method with Parameters
	// NEW method used for SSO Login
	/*
	 * 
	 * 
	 * public AppiumDriver<MobileElement> Login_SSO(String client1,String
	 * email1,String user1, String pass1) throws InterruptedException, AWTException
	 * {
	 * 
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //WebElement
	 * client = driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.widget.EditText"
	 * )); WebElement welcomeLogin =
	 * driver.findElement(By.xpath("//android.view.View[@content-desc=\"LOG IN\"]"))
	 * ; //WebElement welcomeLogin=
	 * driver.findElement(By.xpath("//android.view.View[contains(@text,'LOG IN')]"))
	 * ;
	 * 
	 * welcomeLogin.click(); System.out.println("Welcome Screen Reached");
	 * 
	 * //click on client //WebElement next = driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View/android.widget.Button"
	 * )); //next.click();
	 * 
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); WebElement
	 * userclient=driver.findElement(By.id("client")); userclient.click();
	 * userclient.sendKeys(client1);
	 * System.out.println("Clientname Entered Successfully");
	 * 
	 * //click next WebElement Next1=driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.widget.Button"
	 * )); Next1.click(); Thread.sleep(7000);
	 * 
	 * //Click on SSO Link WebElement SSOLink=driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button[2]"
	 * )); SSOLink.click(); Thread.sleep(5000); System.out.println("SSO clicked");
	 * 
	 * //Enter Email WebElement email=driver.findElement(By.id("i0116"));
	 * email.click(); email.sendKeys(email1);
	 * System.out.println("Email Entered Successfully");
	 * 
	 * //click next or enter Robot abc= new Robot();
	 * abc.keyPress(KeyEvent.VK_ENTER); abc.keyRelease(KeyEvent.VK_ENTER);
	 * driver.hideKeyboard(); Thread.sleep(5000);
	 * 
	 * //enter ADFS credentials WebElement
	 * user=driver.findElement(By.id("userNameInput")); user.click();
	 * user.sendKeys(user1); WebElement
	 * pass=driver.findElement(By.id("passwordInput")); pass.click();
	 * pass.sendKeys(pass1); driver.hideKeyboard(); WebElement
	 * Signin=driver.findElement(By.id("submitButton")); Signin.click();
	 * System.out.println("ADFS credentails entered successfully");
	 * 
	 * //continue ADFS WebElement
	 * continue1=driver.findElement(By.id("idSIButton9")); continue1.click();
	 * 
	 * //Allow Location permission //driver.manage().timeouts().implicitlyWait(30,
	 * TimeUnit.SECONDS); WebElement AllowLocation=driver.findElement(By.id(
	 * "com.android.packageinstaller:id/permission_allow_button"));
	 * AllowLocation.click();
	 * 
	 * System.out.println("Technician Login Successful");
	 * driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS); return
	 * driver; //returning driver so that it can be consumed by other classes
	 * 
	 * }
	 * 
	 * 
	 * //Check valid & invalid Parameters public AppiumDriver<MobileElement>
	 * ValidateLoginParams(String client1,String email1,String user1, String pass1)
	 * throws InterruptedException, AWTException {
	 * 
	 * //Click Login Button driver.manage().timeouts().implicitlyWait(30,
	 * TimeUnit.SECONDS); //WebElement client = driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.widget.EditText"
	 * )); WebElement welcomeLogin =
	 * driver.findElement(By.xpath("//android.view.View[@content-desc=\"LOG IN\"]"))
	 * ; //WebElement welcomeLogin=
	 * driver.findElement(By.xpath("//android.view.View[contains(@text,'LOG IN')]"))
	 * ; welcomeLogin.click(); System.out.println("Welcome Screen Reached");
	 * 
	 * //Enter Client driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS); WebElement userclient=driver.findElement(By.id("client"));
	 * userclient.click(); userclient.sendKeys(client1); driver.hideKeyboard();
	 * 
	 * //click next WebElement Next1=driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.widget.Button"
	 * )); Next1.click(); Thread.sleep(10000);
	 * 
	 * // if wrong client entered try { //Wrong client text error message -and
	 * closing app - terminating script driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button"
	 * )).isDisplayed(); System.out.println("Wrong Clientname Entered");
	 * driver.closeApp(); } catch(NoSuchElementException e) {
	 * System.out.println("Client Name is valid"); }
	 * 
	 * //click on SSO button WebElement SSOLink=driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button[2]"
	 * )); SSOLink.click(); Thread.sleep(5000); System.out.println("SSO clicked");
	 * 
	 * 
	 * 
	 * //Enter Email WebElement email=driver.findElement(By.id("i0116"));
	 * email.click(); email.sendKeys(email1);
	 * System.out.println("Email Entered Successfully");
	 * 
	 * //click next or enter /*Robot abc= new Robot();
	 * abc.keyPress(KeyEvent.VK_ENTER); abc.keyRelease(KeyEvent.VK_ENTER);
	 * driver.hideKeyboard(); Thread.sleep(10000);
	 */

	// WebElement continue2=driver.findElement(By.id("idSIButton9"));
	// continue2.click();

	// 1- try catch to invalid email

	/*
	 * try { //String continueTex1t=driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.widget.TextView"
	 * )).getText(); WebElement error = driver.findElement(By.id("usernameError"));
	 * 
	 * if(error.isDisplayed()) { System.out.println("email error message");
	 * driver.closeApp(); } else { System.out.println("email is good"); } }
	 * 
	 * 
	 * catch(Exception exp) { exp.getCause(); }
	 * 
	 * System.out.println("Moving forward with enter email & password"); //enter
	 * ADFS credentials WebElement user=driver.findElement(By.id("userNameInput"));
	 * user.click(); user.sendKeys(user1); WebElement
	 * pass=driver.findElement(By.id("passwordInput")); pass.click();
	 * pass.sendKeys(pass1); driver.hideKeyboard(); WebElement
	 * Signin=driver.findElement(By.id("submitButton")); Signin.click();
	 * Thread.sleep(10000);
	 * 
	 * if (driver.findElement(By.id("errorText")).isDisplayed()) {
	 * System.out.println("wrong cred ADFS"); driver.closeApp(); } else {
	 * System.out.println("ADFS credentails entered successfully"); }
	 * 
	 * //2 - try catch for wrong cred try { //String
	 * continueTex1t=driver.findElement(By.xpath(
	 * "//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.widget.TextView"
	 * )).getText(); WebElement error = driver.findElement(By.id("errorText"));
	 * 
	 * if(error.isDisplayed()) { System.out.println("wrong cred message");
	 * driver.closeApp(); } else { System.out.println("cred is good"); } }
	 * 
	 * 
	 * catch(Exception exp) { exp.getCause(); }
	 * 
	 * //continue ADFS WebElement
	 * continue1=driver.findElement(By.id("idSIButton9")); continue1.click(); //}
	 * 
	 * 
	 * //Allow Location permission //driver.manage().timeouts().implicitlyWait(30,
	 * TimeUnit.SECONDS); WebElement AllowLocation=driver.findElement(By.id(
	 * "com.android.packageinstaller:id/permission_allow_button"));
	 * AllowLocation.click();
	 * 
	 * System.out.println("Technician Login Successful");
	 * driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS); //return
	 * driver; //returning driver so that it can be consumed by other classes return
	 * driver; }
	 */

	/*
	 * else { System.out.println("Some other issue"); }
	 */
	// }
	/*
	 * catch(Exception e) { e.getCause(); e.getMessage(); }
	 */

	// }

	// Function to Fetch Excel Data from cell ( data in String but accepted for all
	// types-formatter class) - USED

	/*
	 * public static String getCellData_String(int rowNum, int colNum) { String
	 * datacell = null; String excelFilePath =
	 * "C:\\Users\\sgrover7\\Desktop\\WorkshopHub_Chevin\\src\\test\\java\\tests_Android\\AndroidTestData.xlsx";
	 * // C:\Users\sgrover7\Desktop\WorkshopHub_Chevin\src\test\java\tests_Android\
	 * AndroidTestData try {
	 * 
	 * FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 * XSSFWorkbook workbook = new XSSFWorkbook(inputStream); XSSFSheet Sheet =
	 * workbook.getSheetAt(0); DataFormatter formatter = new DataFormatter(); Object
	 * data = formatter.formatCellValue(Sheet.getRow(rowNum).getCell(colNum));
	 * datacell = data.toString().trim(); //
	 * System.out.println("Cell Data---"+data);
	 * 
	 * } catch (Exception e) { System.out.println(e.getCause());
	 * System.out.println(e.getMessage()); // printStackTrace(e); } return datacell;
	 * 
	 * } //@Test //(priority=2) //void CreatWOTEST() {
	 * //CreateWorkOrder("1FADP3F20JL225130");
	 * 
	 * //} /* public AppiumDriver<MobileElement> CreateWorkOrder(String VIN){
	 * 
	 * driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	 * 
	 * System.out.println("s1");
	 * 
	 * MobileElement CreateWo= driver.findElement(By.xpath(
	 * "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[5]/android.view.View/android.view.View"
	 * )); CreateWo.click();
	 * 
	 * MobileElement Search=driver.findElement(By.xpath(
	 * "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.widget.EditText"
	 * )); Search.click(); Search.sendKeys(VIN); driver.hideKeyboard();
	 * MobileElement WO=driver.findElement(By.xpath(
	 * "//android.view.View[contains(@text,'1FADP3F20JL225130')]")); WO.click();
	 * 
	 * //fetch all WO details //edit existing MobileElement
	 * Comment=driver.findElement(By.
	 * xpath("//android.widget.EditText[contains(@text,'Comments *')]"));
	 * Comment.click(); Comment.sendKeys("adding WO - Savi app test");
	 * driver.hideKeyboard();
	 * 
	 * 
	 * return driver;
	 * 
	 * }
	 */

	public AppiumDriver<MobileElement> ScrollToVisibleElement(WebElement createWo, String text) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Find element by link text and store in variable "Element"
		WebElement Element = driver.findElement(By.linkText(text));

		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", Element);
		return driver;
	}

	/*
	 * @SuppressWarnings("unchecked") public void scrollAndClick(String visibleText)
	 * { ((FindsByAndroidUIAutomator<MobileElement>) driver).
	 * findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
	 * +visibleText+"\").instance(0))").click(); }
	 */

	@AfterSuite
	
	 public void teardown() { 
		// driver.close(); //driver.quit(); 
		}
	}
	 
