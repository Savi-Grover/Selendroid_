package tests_Android;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TestCase6_TechnicianClockOff_RepairLine {
@Test
	public void ClockOff() {
		BaseClass abc = new BaseClass(); // calling setup and login methods from BASE
		abc.setup();
		abc.Login("fordmustang", "Savi", "Ford1234");
		AppiumDriver<MobileElement> driver1 = BaseClass.driver;
		driver1.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// click on Work Orders
		//WebElement ele1 = driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[2]/android.view.View/android.view.View[contains(@text,'Work Orders')]"));
		//ele1.click();
		
		// click on one WO
		//MobileElement WO4 = driver1.findElement(By.xpath("//android.view.View[4]/android.view.View[2]/android.view.View"));
		//WO4.click();
		// click on one RL
		// hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[4]/android.view.View[3]/android.view.View/android.view.View
		 MobileElement RL1=
		 driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[4]/android.view.View[3]/android.view.View/android.view.View"));
		 String RepairLine= RL1.getText();
		 System.out.println("Repair Line Desc---"+RepairLine);
	     RL1.click();
	     
		WebElement WO1 = driver1.findElement(
				By.xpath("//android.view.View[contains(@text,'1FDXW47F1YEE07298 X - SFW000301 IN PROGRESS')]"));
		WO1.click();
		WebElement RL2 = driver1.findElement(By.xpath(
				"//android.view.View[contains(@text,'000006 GCSC 558412 Driving Performance, Engine Surge, Deceleration, Cold PAUSED')]"));
		RL2.click();
		System.out.println("Repair line desc---" + RL1.getText());
		WebElement START = driver1.findElement(By.xpath("//android.view.View[contains(@text,'START')]"));
		START.click();
		// verify header
		WebElement WoText = driver1
				.findElement(By.xpath("//android.view.View[1][contains(@text,'1FDXW47F1YEE07298')]"));
		WoText.click();
		// clock off
		WebElement ClockOff = driver1.findElement(
				By.xpath("android.view.View[7]/android.view.View/android.view.View[contains(@text,'Clock Off Job')]"));
		ClockOff.click();
		// add desc
		WebElement DescFW = driver1.findElement(By.xpath("android.widget.EditText[contains(@text,'DESCRIPTION_FW')]"));
		DescFW.sendKeys("Clocking Off");
		WebElement Save = driver1.findElement(By.xpath("android.widget.Button[contains(@text,'Save')]"));
		Save.click();
		// Log OUT
		WebElement LogOUT = driver1.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View"));
		LogOUT.click();
	}

}
