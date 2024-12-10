package tests_Android;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class oldLogin {

	
	@Test
	
	//Basic Authentication
	public void LoginTech() {
		
		BaseClass abc =new BaseClass();
		abc.setup();
		abc.LoginBasicAuth_OLD("FordQA","Sgrover7","Ford1234");
		
	}
}
