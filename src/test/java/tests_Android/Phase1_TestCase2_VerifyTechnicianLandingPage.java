package tests_Android;

import org.testng.annotations.Test;
import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Phase1_TestCase2_VerifyTechnicianLandingPage {

	
		// Pre-requisite - 1. Remove mobile browser history / cache.
		// Pre-requisite - 2. clean ADFS saved passwords saved in mobile browser.
		// Pre-requisite - 3. Maintain CDSID user hierarchy in terminal and technician record in proper Ford env (dev/qa)
		//otherwise testcase will fail
	    // Pre-requisite - 4. Start Appium server before running
	
	@Test
	public void MenuOptions() throws InterruptedException, AWTException {

		//Inheriting Base Class
		BaseClass abc =new BaseClass();
				
		//Declare Test Data in sheet
		String clientname=BaseClass.getCellData_String(2, 1);
		String email=BaseClass.getCellData_String(2, 2);
		String username=BaseClass.getCellData_String(2, 3);
		String passwd=BaseClass.getCellData_String(2, 4);
				
		//Run setup and Login
		abc.setup();
		abc.Login_SSO(clientname,email,username,passwd);
		
		//Take values of menu from data sheet and compare from there on screen

		AppiumDriver<MobileElement> driver1 = BaseClass.driver;
		driver1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		// declare all menu options
		String expectedHeading1 = BaseClass.getCellData_String(2, 5);
		String expectedHeading2 = BaseClass.getCellData_String(2, 6);
		String expectedHeading3 = BaseClass.getCellData_String(2, 7);
		String expectedHeading4 = BaseClass.getCellData_String(2, 8);
		String expectedHeading5 = BaseClass.getCellData_String(2, 9);
		String expectedHeading6 = BaseClass.getCellData_String(2, 10);
		String expectedHeading7 = BaseClass.getCellData_String(2, 11);
		

		String menu1 = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View")).getText();
		// String abc=menu1.getText();
		/*
		 * if (abc.contains(expectedHeading1)) {
		 * System.out.println("The expected heading is same as actual heading --- "
		 * +menu1); }
		 */
		System.out.println(menu1);
		// System.out.println(menu1);
		String menu2 = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View")).getText();
		System.out.println(menu2);
		// System.out.println("The expected heading is same as actual heading ---
		// "+menu1);
		String menu3 = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View")).getText();
		//System.out.println(menu3);
		String menu4 = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View")).getText();	
		//System.out.println(menu4);
		String menu5 = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View")).getText();	
		//System.out.println(menu5);
		String menu6 = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[6]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View")).getText();	
		//System.out.println(menu6);
		String menu7 = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[7]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View")).getText();	
		//System.out.println(menu7);

		// System.out.println(menu1);

		if (menu1.contains(expectedHeading1) && menu2.contains(expectedHeading2) && menu3.contains(expectedHeading3)
				&& menu4.contains(expectedHeading4) && menu5.contains(expectedHeading5)
				&& menu6.contains(expectedHeading6) && menu7.contains(expectedHeading7)) {
			System.out.println("The expected heading is same as actual heading --- " + menu1);
			System.out.println("The expected heading is same as actual heading --- " + menu2);
			System.out.println("The expected heading is same as actual heading --- " + menu3);
			System.out.println("The expected heading is same as actual heading --- " + menu4);
			System.out.println("The expected heading is same as actual heading --- " + menu5);
			System.out.println("The expected heading is same as actual heading --- " + menu6);
			System.out.println("The expected heading is same as actual heading --- " + menu7);
			
			System.out.println("Menu Options are verified in Technician Landing Page");
		}

		else {
			// End Shift Now in place of Start My Shift

			String expectedHeading88 = "End My Shift";
			String expectedHeading9 =  "Work Orders";
			String expectedHeading10 = "Tasks";
			String expectedHeading11 = "Timesheet";
			String expectedHeading12 = "Create Work Order";
			String expectedHeading13 = "Assist";
			String expectedHeading14 = "Vehicle Details";

			// String
			// menu8=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[1]/android.view.View")).getText();
			// String
			// menu9=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[2]/android.view.View/android.view.View")).getText();
			// System.out.println("The expected heading is same as actual heading ---
			// "+menu1);
			// String
			// menu10=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[3]/android.view.View/android.view.View")).getText();
			// String
			// menu11=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[4]/android.view.View/android.view.View")).getText();
			// String
			// menu12=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[5]/android.view.View/android.view.View")).getText();
			// String
			// menu13=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[6]/android.view.View/android.view.View")).getText();
			// String
			// menu14=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[7]/android.view.View/android.view.View")).getText();

			WebElement ele1 = driver1.findElement(By.xpath("//android.view.View[contains(@text,'End My Shift')]"));
			WebElement ele2 = driver1.findElement(By.xpath("//android.view.View[contains(@text,'Work Orders')]"));
			WebElement ele3 = driver1.findElement(By.xpath("//android.view.View[contains(@text,'Tasks')]"));
			WebElement ele4 = driver1.findElement(By.xpath("//android.view.View[contains(@text,'Timesheet')]"));
			WebElement ele5 = driver1.findElement(By.xpath("//android.view.View[contains(@text,'Create Work Order')]"));
			WebElement ele6 = driver1.findElement(By.xpath("//android.view.View[contains(@text,'Assist')]"));
			WebElement ele7 = driver1.findElement(By.xpath("//android.view.View[contains(@text,'Vehicle Details')]"));

			if (ele1.isDisplayed() && ele2.isDisplayed() && ele3.isDisplayed() && ele4.isDisplayed()
					&& ele5.isDisplayed() && ele6.isDisplayed() && ele7.isDisplayed()) {

				System.out.println("The expected landing page is for technician whose shift is Ongoing --- ");
				System.out.println("The expected heading is same as actual heading --- " + ele1.getText());
				System.out.println("The expected heading is same as actual heading --- " + ele2.getText());
				System.out.println("The expected heading is same as actual heading --- " + ele3.getText());
				System.out.println("The expected heading is same as actual heading --- " + ele4.getText());
				System.out.println("The expected heading is same as actual heading --- " + ele5.getText());
				System.out.println("The expected heading is same as actual heading --- " + ele6.getText());
				System.out.println("The expected heading is same as actual heading --- " + ele7.getText());
			}
		}
	}

	// WebElement
	// EndShift=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[1]/android.view.View"));
	// EndShift.click();

}

// log out after verifying

