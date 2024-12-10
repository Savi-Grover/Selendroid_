package tests_Android;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;
import tests_Android.BaseClass;

public class Phase1_TestCase3_RepairLineClockOnOff {


	// Pre-requisite - 1. Remove mobile browser history / cache.
	// Pre-requisite - 2. clean ADFS saved passwords saved in mobile browser.
	// Pre-requisite - 3. Maintain CDSID user hierarchy in terminal and technician record in proper Ford env (dev/qa)
	//otherwise testcase will fail
	// Pre-requisite - 4. Start Appium server before running

	@Test
	public void WOScreenTechnician() throws InterruptedException, AWTException {

		//Inheriting Base Class
		BaseClass abc =new BaseClass();

		//Trigger app
		abc.setup();

		//Declare Test Data in sheet
		String clientname=BaseClass.getCellData_String(2, 1);
		String email=BaseClass.getCellData_String(2, 2);
		String username=BaseClass.getCellData_String(2, 3);
		String passwd=BaseClass.getCellData_String(2, 4);
		abc.Login_SSO(clientname,email,username,passwd);

		//Inheriting Previous Class
		//Phase1_TestCase2_VerifyTechnicianLandingPage abcd =new Phase1_TestCase2_VerifyTechnicianLandingPage();

		//Run verify Technician LP - calling old test case from here
		//abcd.MenuOptions();

		//Take values of menu from data sheet and compare repair line options from there on screen
		AppiumDriver<MobileElement> driver1 = BaseClass.driver;
		driver1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		//click on Start My shift & verify technician name by ADFS username, start time
		WebElement StartShift= driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[5]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View"));
		StartShift.click();

		String AdfcUsername=BaseClass.getCellData_String(3, 3);
		String appTechName = driver1.findElement(By.xpath("")).getText();

		if (AdfcUsername.equalsIgnoreCase(appTechName)) {
			System.out.println("Technician Verified same as ADFC username");
		}
		else {

		}

		//Declare Test Data in sheet
		String EndMyShift1=BaseClass.getCellData_String(3, 13);
		String WO=BaseClass.getCellData_String(3, 14);
		String Tasks=BaseClass.getCellData_String(3, 15);
		String Timesheet=BaseClass.getCellData_String(3, 16);
		String WorkWaiting=BaseClass.getCellData_String(3, 17);
		String Assist=BaseClass.getCellData_String(3, 18);
		String VehDetails=BaseClass.getCellData_String(3, 19);




		//Repair Line Summary Page - menu Options

		WebElement ele1= driver1.findElement(By.xpath("//android.view.View[contains(@text,'End My Shift')]"));
		WebElement ele2= driver1.findElement(By.xpath("//android.view.View[contains(@text,'Work Orders')]"));
		WebElement ele3= driver1.findElement(By.xpath("//android.view.View[contains(@text,'Tasks')]"));
		WebElement ele4= driver1.findElement(By.xpath("//android.view.View[contains(@text,'Timesheet')]"));
		WebElement ele5= driver1.findElement(By.xpath("//android.view.View[contains(@text,'Create Work Order')]"));
		WebElement ele6= driver1.findElement(By.xpath("//android.view.View[contains(@text,'Assist')]"));
		WebElement ele7= driver1.findElement(By.xpath("//android.view.View[contains(@text,'Vehicle Details')]"));

		if (ele1.isDisplayed()&&ele2.isDisplayed()&&ele3.isDisplayed()&&ele4.isDisplayed()&&ele5.isDisplayed()&&ele6.isDisplayed()&&ele7.isDisplayed())
		{

			System.out.println("The expected landing page is for technician whose shift is Ongoing --- ");
			System.out.println("The expected heading is same as actual heading --- "+ele1.getText());
			System.out.println("The expected heading is same as actual heading --- "+ele2.getText());
			System.out.println("The expected heading is same as actual heading --- "+ele3.getText());
			System.out.println("The expected heading is same as actual heading --- "+ele4.getText());
			System.out.println("The expected heading is same as actual heading --- "+ele5.getText());
			System.out.println("The expected heading is same as actual heading --- "+ele6.getText());
			System.out.println("The expected heading is same as actual heading --- "+ele7.getText());
		}


		//click Workorders
		ele2.click();


		//identify open and in progress tickets
		//List<MobileElement> Open= driver1.findElements(By.xpath("//android.view.View[contains(@text,'OPEN')]"));
		List<MobileElement> InProgress= driver1.findElements(By.xpath("//android.view.View[contains(@text,'IN PROGRESS')]"));
		//System.out.println("number of open work-orders:   "+Open.size());
		System.out.println("number of in-progress work-orders:   "+InProgress.size());

		//click on Back button
		/*WebElement Back = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View"));
		Back.click();
		System.out.println("Back button Clicked from WO Screen");*/
	}




}
