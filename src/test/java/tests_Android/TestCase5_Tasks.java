package tests_Android;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TestCase5_Tasks {
@Test
public void TaskScreenOperations() {
BaseClass abc =new BaseClass();           //calling setup and login methods from BASE
abc.setup();
abc.Login("fordmustang","richc","richc");
//Click on Tasks and choose a value for technician
AppiumDriver<MobileElement> driver1=BaseClass.driver;
driver1.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//MobileElement Tasks= driver1.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View[1]/android.view.View/android.view.View"));
String Tasks= driver1.findElement(By.xpath("//android.view.View[contains(@text,'Tasks')]")).getText();
driver1.findElement(By.xpath("//android.view.View[contains(@text,'Tasks')]")).click();
driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//verify all elemenets
String expectedHeading1 ="Administration";
String expectedHeading2 ="Assisting Others";
String expectedHeading3 ="Cleaning";
String expectedHeading4 ="Get Parts";
String expectedHeading5 ="Courses/Training";
String expectedHeading6 ="Moving Vehicles";
String expectedHeading7 ="Meals/Breaks";
String expectedHeading8 ="Sick Time-Unscheduled";
String expectedHeading9 ="Supervision";
String expectedHeading10 ="Waiting";
String menu1= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Administration')]")).getText();
String menu2= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Assisting Others')]")).getText();
String menu3= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Cleaning')]")).getText();
String menu4= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Get Parts')]")).getText();
String menu5= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Courses/Training')]")).getText();
String menu6= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Moving Vehicles')]")).getText();
String menu7= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Meals/Breaks')]")).getText();
String menu8= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Sick Time-Unscheduled')]")).getText();
String menu9= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Supervision')]")).getText();
String menu10= driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Waiting')]")).getText();
if(menu1.contains(expectedHeading1)&&menu2.contains(expectedHeading2)&&menu3.contains(expectedHeading3)&&menu4.contains(expectedHeading4)&&menu5.contains(expectedHeading5)&&menu6.contains(expectedHeading6)&&menu7.contains(expectedHeading7)
&&menu8.contains(expectedHeading8)&&menu9.contains(expectedHeading9)&&menu10.contains(expectedHeading10))
{
System.out.println("The expected heading is same as actual heading --- "+menu1);
System.out.println("The expected heading is same as actual heading --- "+menu2);
System.out.println("The expected heading is same as actual heading --- "+menu3);
System.out.println("The expected heading is same as actual heading --- "+menu4);
System.out.println("The expected heading is same as actual heading --- "+menu5);
System.out.println("The expected heading is same as actual heading --- "+menu6);
System.out.println("The expected heading is same as actual heading --- "+menu7);
System.out.println("The expected heading is same as actual heading --- "+menu8);
System.out.println("The expected heading is same as actual heading --- "+menu9);
System.out.println("The expected heading is same as actual heading --- "+menu10);
}
else {
System.out.println("The expected heading is not same as actual heading --- ");
}
//click on breaks
MobileElement Breaks=driver1.findElement(By.xpath("//android.view.View[2]/android.view.View[contains(@text,'Meals/Breaks')]"));
Breaks.click();
Breaks.click();
//click on alert confirm
Alert alert = driver1.switchTo().alert();
alert.accept();
//capabilities.SetCapability("autoAcceptAlerts", true);

}
}
