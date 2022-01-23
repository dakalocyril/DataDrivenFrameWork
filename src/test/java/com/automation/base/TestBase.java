package com.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {
	
	//public static String browser;
	/*Will initialize the below
	 * WebDriver
	 * Properties
	 * Logs
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 */
	//public static WebDriver driver;
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
		
	//public static Logger log = LogManager.getLogger("TestBase.class");
	//public static Logger log = LogManager.getLogger(TestBase.class.getName());
	//public static Logger log = Logger.ge
	//public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	//public static ExtentReports report = ExtentManager.getInstance();
	//public static ExtentTest test;
	public static String driverPath = System.getProperty("user.dir") + "\\src\\test\\resources\\executables";
	String log4jConfPath = "C:\\Users\\Ngwana DC\\eclipse-workspace\\DataDrivenFrameWork\\src\\test\\resources\\properties\\log4j.properties";
	//PropertyComparator.c
	//PropertyConfigurator.configure(log4jConfPath);
	
	//PropertyConfigurator.co

	//@Before
	@BeforeSuite
	public void setUp() {
		
		//PropertyConfigurator.configure("log4j2.xml");
		//PropertyConfigurator.configure(log4jConfPath);
		
		if (driver == null) {
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				//log.info("Config file loaded");
				//log.d
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(config.getProperty("browser").equalsIgnoreCase("chrome")) {
	        	System.setProperty("webdriver.chrome.driver", driverPath + ("\\chromedriver.exe"));
	        	driver = new ChromeDriver(); 
	        	//log.info("Chrome has been selected");
	            //ChromeOptions options = new ChromeOptions();
	            //options.setExperimentalOption("useAutomationExtension", true);
	            //options.addArguments("--headless");
	            //driver = new ChromeDriver(options);
	            //log.debug("Chrome launched");
	            //log.info("Chrome launched");
			}
			
			else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
	        	System.setProperty("webdriver.ie.driver", driverPath + ("\\IEDriverServer.exe"));
	            driver = new InternetExplorerDriver();
			}
			
			else if(config.getProperty("browser").equalsIgnoreCase("edge")) {
	        	System.setProperty("webdriver.edge.driver", driverPath + ("\\msedgedriver.exe"));
	        	driver = new EdgeDriver();
	        	//log.info("MS Edge has been selected");
			}
			
			else if(config.getProperty("browser").equalsIgnoreCase("firefox")) {
	        	System.setProperty("webdriver.gecko.driver", driverPath + ("\\geckodriver.exe"));
	        	driver = new FirefoxDriver();
			}
			
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);

//			
//			//log.debug("Navigated to : " + config.getProperty("testsiteurl"));
//			
//			//			wait = new WebDriverWait(driver, 5);
		}

	}
	

	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		
		Reporter.log("Clicking on " +locator);
		
		//CustomListeners.report.get().log(Status.INFO, "Clicking on : " + locator);
	}
	
	public void clickOnAlert() {
		
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		//Assert.assertTrue(alert.getText().contains("Customer added successfully"));
		driver.switchTo().alert().accept();
	}

	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		
		//test.log(Status.INFO, "Typing in "+locator + " entered value "+value);
		

//		CustomListeners.testReport.get().log(Status.INFO, "Typing in : " + locator + " entered value as " + value);

	}
	
	static WebElement dropdown; 
	
	public void select(String locator, String value, int index) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByIndex(index);
		//select.selectByVisibleText(value);
		
		//test.log(LogStatus.INFO, "Selecting from dropdown : "+locator+" value as "+ value);
		
	}
	
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
		}
		
		catch(NoSuchElementException e) {
			return false;
			
		}
		
	}
		
	public static void verifyEquals(String expected, String actual) throws IOException {
		
		try {
			Assert.assertEquals(actual, expected);
		}
		
		catch(Throwable t) {
			
			
			//TestUtil.captureScreenshot();
			
			//ReportNG
			//Reporter.log("<br>"+"Verification failure : "+t.getMessage()+"<br>");
			//Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
			//Reporter.log("<br>");
			//Reporter.log("<br>");
			
			//Extent Report
			//test.log(LogStatus.FAIL, "Verification failed with exception : "+t.getMessage());
			//test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			

		}
		
	}
	
	/*
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;


	}
	*/
	
	
	@AfterSuite
	public void tearDown() {
		
			driver.quit();
	}

}	
