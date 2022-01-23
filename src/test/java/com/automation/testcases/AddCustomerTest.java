package com.automation.testcases;

import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.automation.base.TestBase;
import com.automation.utilities.TestUtil;

public class AddCustomerTest extends TestBase {
	
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException{
		
		if(!data.get("runmode").equals("Y")){
			
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		
		
		click("addCustBtn_CSS");
		type("firstname_CSS",data.get("firstname"));
		type("lastname_XPATH",data.get("lastname"));
		type("postcode_CSS",data.get("postcode"));
		click("addbtn_CSS");
		Thread.sleep(4000);
		
		clickOnAlert();
	}
	
	

}
