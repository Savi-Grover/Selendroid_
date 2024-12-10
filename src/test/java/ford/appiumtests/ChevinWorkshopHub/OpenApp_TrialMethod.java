package ford.appiumtests.ChevinWorkshopHub;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class OpenApp_TrialMethod {

	static AppiumDriver driver;
	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			openWorkshopApp();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	@BeforeTest
	//just a script to trigger app on connected device
	public static void openWorkshopApp() throws MalformedURLException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability("deviceName","Samsung s22");
		cap.setCapability("udid","ZY224VM3HD");  //taken from adb devices
		cap.setCapability("platformName","Android");
		cap.setCapability("platformVersion","12.0");
		//cap.setCapability("automationName","UiAutomator1"); //uiautomator 2
		
		//took from apk info app - playstore
		cap.setCapability("appPackage","com.chevin.workshophub");
		
		cap.setCapability("appActivity","com.chevin.workshophub.MainActivity"); //add splash activity instead of mainactivity
		
		//passing appium host URL
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		// assigning appium url and capabilities to driver
		driver = new AppiumDriver(url,cap);
		//DesiredCapabilities cap = new DesiredCapabilities();
		//cap.setCapability(MobileCapabilityType.DEVICE_NAME, "0260dd0730971a0a");
		//cap.setCapability(MobileCapabilityType.APP, "/home/manoj/Downloads/xyz.apk");
		//AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);


		System.out.println("App Started");
	}
	

}
