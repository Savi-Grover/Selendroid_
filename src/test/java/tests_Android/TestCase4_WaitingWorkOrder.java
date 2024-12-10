package tests_Android;

import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.remote.HideKeyboardStrategy;

public class TestCase4_WaitingWorkOrder {
	
	
	@Test
	public void WorkOrderElements() {
		
		//App Launch and Login
		BaseClass abc =new BaseClass();           //calling setup and login methods from BASE
		abc.setup();
		abc.Login("ford","Savi","Ford1234");
	
		//Choose waiting WO from Menu
		System.out.println("Entering Waiting WO Screen");
		AppiumDriver<MobileElement> driver1=BaseClass.driver;
		driver1.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		//MobileElement ele2= driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[6]/android.view.View/android.view.View"));
		MobileElement ele2= driver1.findElement(By.xpath("//android.view.View[contains(@text,'Work Waiting')]"));
		
		ele2.click();
		driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Identify Elements
		List<MobileElement> Open= driver1.findElements(By.xpath("//android.view.View[contains(@text,'OPEN')]"));
		List<MobileElement> InProgress= driver1.findElements(By.xpath("//android.view.View[contains(@text,'IN PROGRESS')]"));
		System.out.println("number of open work-orders:   "+Open.size());
		System.out.println("number of in-progress work-orders:   "+InProgress.size());
		
		/*
		//Search Field Operation
		MobileElement SearchBox= driver1.findElement(By.xpath("//android.widget.EditText[contains(@text,'Search')]"));
		SearchBox.sendKeys("1FM");
		driver1.hideKeyboard();
		driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//^search by Wo number
		//^ search by SWo Status
		System.out.println("Search WO by VIN");
		
		
		//Wo Order Dropdown Operation
		WebElement DropDown1= driver1.findElement(By.xpath("//android.view.View[contains(@text,'1FM5K8D82JGB41686 X - SFW000306 IN PROGRESS')]"));
		DropDown1.click();
		
		//000001 GCSC 775938 Lighting/Glass/Vision, Exterior Lighting, Tail Lamp, 
		WebElement Ticket1=driver1.findElement(By.xpath("//android.view.View[contains(@text,'Inoperative IN PROGRESS')]"));
		String WoDesc=Ticket1.getText();
		System.out.println("Wo description Summary -   "+WoDesc);
		
		//Click on WO ticket
		Ticket1.click();
		WebElement CancelButton=driver1.findElement(By.xpath("//android.widget.Button[contains(@text,'Cancel')]"));
		CancelButton.click();
		System.out.println("Cancel Clicked");
		Ticket1.click();
		WebElement StartButton=driver1.findElement(By.xpath("//android.widget.Button[contains(@text,'Start')]"));
		StartButton.click();
		System.out.println("Start Button Clicked on WO");
		driver1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Scroll Down
		TouchActions action = new TouchActions(driver1);
		WebElement Attachments=driver1.findElement(By.xpath("//android.view.View[contains(@text,'Attachments')]"));
		action.scroll(Attachments, 40, 100);
		action.perform();
		System.out.println("scroll performed");

		//Show menu inside AFter Starting a WO
		/*Vehicle Detail
		
		Part Request
		
		ECAT
		
		PTS
		
		
		Create Repair Line
		
		
		Amend Repair Line
		
		
		LANDF
		
		
		LABOROPCODES
		
		Clock Off Job
		
		*/
		
		
		
		
		
		
		
		
	}
	

}
