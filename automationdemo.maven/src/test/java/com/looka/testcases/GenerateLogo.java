/**
 * 
 */
package com.looka.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentReport;
import com.looka.pages.CommonLocators;
import com.looka.pages.DashboardPage;
import com.looka.pages.EditorPage;
import com.looka.pages.ExplorePage;
import com.looka.pages.LoginPage;
import com.looka.pages.OnboardingPage;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;

/**
 * @author JoshZhuang
 *
 */

@Listeners(utilities.auto.TestNGListener.class)

public class GenerateLogo {
	WebDriver ldriver;
	String reportfolder = "Lookatests";
	String testcasename = "Lookatests";
	String browsername = "chrome";
	String url = "https://www.looka.com/";
	

	@BeforeClass // init extreport
	public void initExtentReport() throws IOException {
		ExtentReport.createTestReport(reportfolder, testcasename); //init extent report
	}
	
	@AfterClass //close chrome and flush extreport
	public void closeApp() throws IOException {

		//		driver.quit();  //close browser
		DriverFactory.getInstance().removeDriver(); //close current threadlocal browser instance --- for multi-threading
		
		//finalise test report
		ExtentReport.flushReports(reportfolder);
	}

	
	
	@Test
	public void addLogoToSaved() throws Exception {
		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
//		ExtentReport.createTestReport(reportfolder, reportfolder); //init extent report
		
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
		
		//init LoginPage
		LoginPage loginpage = PageFactory.initElements(ldriver, LoginPage.class);	
		
		//login
		loginpage.login("joshzhuangdemo@gmail.com","K!e9R#cj4KRXQ7w");
		
		
		//init LoginPage
		CommonLocators commonlocators = PageFactory.initElements(ldriver, CommonLocators.class);		
		
		//check if login successful
		commonlocators.checkUserLogin();
		
		//navigate to logo generator
		commonlocators.navigateToGenerator();
		
		//init onboarding page
		OnboardingPage onboardingpage = PageFactory.initElements(ldriver, OnboardingPage.class);
		
		//check if user landed to onboarding page successfully
		onboardingpage.checkOnboarding();
		
		//generate a logo
		onboardingpage.generateLogo();
		
		//init ExplorePage page
		ExplorePage explorepage = PageFactory.initElements(ldriver, ExplorePage.class);
		
		//check if logos are being generated within x seconds
		explorepage.checkLogoGenerating(3);
		
		//check if logos are generated within x seconds
		explorepage.checkGeneratedLogos(12);
		
		Thread.sleep(2000);
		
		//select 3rd photo to save
		explorepage.selectSavedLogo(3);
		
		
		//init EditorPage page
		EditorPage editorpage = PageFactory.initElements(ldriver, EditorPage.class);
		String logoid = editorpage.getCurrentLogoID();
		
		//navigate to dashboard page
		commonlocators.navigateToSavedlogos();
		
		
		//init dashboard page
		DashboardPage dashboardpage = PageFactory.initElements(ldriver, DashboardPage.class);


		//Check if logo with specific ID is displayed
		dashboardpage.checkSavedLogo(logoid);
		
		Thread.sleep(2000);
		
		
		
	}

}
