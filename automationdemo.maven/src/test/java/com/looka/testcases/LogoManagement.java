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

public class LogoManagement {
	WebDriver ldriver;
	String reportfolder = "Lookatests";
	String testcasename = "Lookatests";
	String browsername = "chrome";
	String url = "https://www.looka.com/";
	
	
	@BeforeClass // init extreport
	public void initExtentReport() throws IOException, Exception {
		ExtentReport.createTestReport(reportfolder, testcasename); //init extent report
	}

	@BeforeClass (dependsOnMethods={"initExtentReport"}) //init browser instance and launch app
	public void launchApp() throws Exception {
		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
	}
	

	@AfterClass //close chrome and flush extreport
	public void closeApp() throws IOException {

		//		driver.quit();  //close browser
		DriverFactory.getInstance().removeDriver(); //close current threadlocal browser instance --- for multi-threading
		
		//finalise test report
		ExtentReport.flushReports(reportfolder);
	}

	
	@Test  (priority=1)
	public void login() throws Exception {
		
		//init pages
		LoginPage loginpage = PageFactory.initElements(ldriver, LoginPage.class);	
		CommonLocators commonlocators = PageFactory.initElements(ldriver, CommonLocators.class);	
		
		//login
		loginpage.login("joshzhuangdemo@gmail.com","K!e9R#cj4KRXQ7w");
		
		//check if login successful
		commonlocators.checkUserLogin();
	}
	
	
	// (dependsOnMethods={"login"}) 
	@Test (priority=2)
	public void addLogoToSaved() throws Exception {
		
		//init pages
		CommonLocators commonlocators = PageFactory.initElements(ldriver, CommonLocators.class);	
		OnboardingPage onboardingpage = PageFactory.initElements(ldriver, OnboardingPage.class);
		ExplorePage explorepage = PageFactory.initElements(ldriver, ExplorePage.class);
		EditorPage editorpage = PageFactory.initElements(ldriver, EditorPage.class);
		DashboardPage dashboardpage = PageFactory.initElements(ldriver, DashboardPage.class);
		
		
		//navigate to logo generator
		commonlocators.navigateToGenerator();
		
		//check if user landed to onboarding page successfully
		onboardingpage.checkOnboarding();
		
		//generate a logo
		onboardingpage.generateLogo();
		
		//check if logos are being generated within x seconds
		explorepage.checkLogoGenerating(3);
		
		//check if logos are generated within x seconds
		explorepage.checkGeneratedLogos(12);
		Thread.sleep(8000);
		
		//select a photo to save
		explorepage.selectSavedLogo(5);
		
		//get logo id from editor page url
		String logoid = editorpage.getCurrentLogoID();
		Thread.sleep(5000);
		
		//navigate to dashboard page
		commonlocators.navigateToSavedlogos();
		Thread.sleep(4000);
		
		//wait a bit for page loading until chat icon shows
		commonlocators.waitForChatIcon(5);
		
		//Check if logo with specific ID is displayed
		dashboardpage.checkSavedLogo(logoid);
		
		Thread.sleep(2000);
	}
	
	
	@Test  (priority=3)
	public void logoff() throws Exception {
		
		//init pages
		CommonLocators commonlocators = PageFactory.initElements(ldriver, CommonLocators.class);	
		LoginPage loginpage = PageFactory.initElements(ldriver, LoginPage.class);
		
		//log off and navigate to homepage
		commonlocators.logOffToHome();
		
		//check if login button exists
		loginpage.checkLoginLabel(5);
	}
	

}
