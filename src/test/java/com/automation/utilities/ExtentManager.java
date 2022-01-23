package com.automation.utilities;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	static ExtentReports extent;
	static Date d = new Date();
	static String fileName = "\\Extent-Report_" + d.toString().replace(":","_").replace(" ", "_") + ".html";	
	public static ExtentReports getReportObject()
	{
		
		String path =System.getProperty("user.dir")+"\\reports";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path+fileName);
		
		reporter.config().setEncoding("utf-8");
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Automation Test Results");
		reporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Tester", "Dakalo Ngwana");
		extent.setSystemInfo("Organisation", "Ngwana DC & Family");
		extent.setSystemInfo("Purpose", "Enhancing Knowledge");
		return extent;
		
	}
}
