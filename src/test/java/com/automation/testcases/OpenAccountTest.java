package com.automation.testcases;

import java.util.Hashtable;
import org.testng.annotations.Test;
import com.automation.base.TestBase;
import com.automation.utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException {
		
		click("openaccount_CSS");
		
		Thread.sleep(2000);
		select("customer_CSS",data.get("customer"), 2);
		
		select("currency_CSS",data.get("currency"),2);
		click("process_CSS");

		//Thread.sleep(5000);
		
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		//alert.accept();
	}        
			
}
