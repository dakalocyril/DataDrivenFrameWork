package com.automation.testcases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.automation.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	//public static Logger log = LogManager.getLogger(TestBase.class.getName());

	@Test
	public void loginAsBankManager() throws InterruptedException, IOException {
		
		Reporter.log("Inside Login Test");
		click("bmlBtn_CSS");
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))));
		//Assert.assertTrue(isElementPresent(By.className(OR.getProperty("hello"))));
		
		Reporter.log("Log in successfully");
		Assert.fail("Testing failure screenshot");
		Thread.sleep(2000);
	}
	
	
}
