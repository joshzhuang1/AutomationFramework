package com.common.TestReporting;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.util.Objects;

import org.testng.Assert;


public class ExtentReport {

	private ExtentReport() {} //constructor
	
	private static ExtentReports extent;
	//public static ExtentTest test;
	
	//init report, specify report folder, load config file
	public static void initReports(String foldername) throws IOException {
		if(Objects.isNull(extent)) { // don't run this if extent object already exists
			extent = new ExtentReports(); //create new extentreport object
			ExtentSparkReporter spark = new ExtentSparkReporter("testreports/"+foldername+"/index.html"); //set report html file path and name
			extent.attachReporter(spark); //Attach a reporter to access all started tests, nodes and logs			
			spark.loadXMLConfig(new File("XMLfiles/extentconfig.xml")); //Load extentconfig.xml file	
		}

	}
	
	
	//	create test: can pass testcasename as loggername for common practice. 
	//	with different logger names, extent report will create different tabs in a html report.
	public static void createTest(String loggername) {
		ExtentTest test = extent.createTest(loggername);
		ExtentManager.setExtentTest(test);
	}
	
	
	//initReports + createTest ----- mostly used
	public static void createTestReport(String foldername,String testcasename) throws IOException {
			initReports(foldername);
			createTest(testcasename);
	}

	
	//tear down report and sync status
	public static void flushReports() throws IOException {
		extent.flush(); //write all the test logs to the report file			
		syncReportResult(); //to fail TestNG report, if Extentreport is a fail.
	}
	
	
	//tear down report, open report, and sync test result
	public static void flushReports(String foldername) throws IOException {
		extent.flush(); //write all the test logs to the report file
		Desktop.getDesktop().browse(new File("testreports/"+foldername+"/index.html").toURI()); //open html report at the end of the test
//		syncReportResult(); //if any failed steps in Extentreport, also fail TestNG report.
	}
	
	
	//Fail TestNG result if ExtentReport status is FAIL. Called by flushReports.
	public static void syncReportResult() {
		Status currentstatus = ExtentManager.getExtentTest().getStatus(); //get pass/fail status of current test
		if (Objects.equals(currentstatus.name(), "FAIL")) { //if extentport status is fail, also fail the TestNG test. Otherwise TestNG report is still pass.
			Assert.fail("Set TestNG result to fail according to extent report results");
		}		
	}
	
}
	

