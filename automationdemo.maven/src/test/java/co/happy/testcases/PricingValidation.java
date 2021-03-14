/**
 * 
 */
package co.happy.testcases;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentReport;
import com.looka.pages.DashboardPage;
import com.looka.pages.LoginPage;
import com.looka.pages.OnboardingPage;

import co.happy.pages.PricingPage;
import co.happy.pages.HomePage;
import co.happy.pages.HPPricingPage;
import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;
import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 *
 */

@Listeners(utilities.auto.TestNGListener.class)

public class PricingValidation {
	WebDriver ldriver;
	String reportfolder = "HappyCoPricing";
	String testcasename = "HappyCoPricing";
	String browsername = "chrome";
	String url = "https://happy.co";
	

	@BeforeClass // init extreport
	public void initExtentReport() throws IOException {
		ExtentReport.createTestReport(testcasename); //init extent report
	}
	
	@AfterClass //close chrome and flush extreport
	public void closeApp() throws IOException {

		//		driver.quit();  //close browser
		DriverFactory.getInstance().removeDriver(); //close current threadlocal browser instance --- for multi-threading
		
		//finalise test report
		ExtentReport.flushReports();
	}

	
	
	@Test
	public void verifyHPPricing() throws Exception {
	
		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
//		ExtentReport.createTestReport(reportfolder, reportfolder); //init extent report
		
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	

		
		
		
		
		//init homepage
		HomePage homepage = PageFactory.initElements(ldriver, HomePage.class);	
		//init pricing page
		HPPricingPage hppricingpage = PageFactory.initElements(ldriver, HPPricingPage.class);	
		//init hppricing page
		PricingPage pricingpage = PageFactory.initElements(ldriver, PricingPage.class);	
		
		homepage.naviToPricing();
		pricingpage.naviToHPPricing();
		
		
		
		hppricingpage.verifyPricing();
		
//		Thread.sleep(3000);
		
//		hppricingpage.verifyPricingSecond();
		
		Thread.sleep(1000);
		
		Assert.fail("END of testing#$D");
		//login
//		loginpage.login("joshzhuangdemo@gmail.com","K!e9R#cj4KRXQ7w");
//		Thread.sleep(1000);
//		dashboardpage.waitlogoloading(5);
//		dashboardpage.deleteLogoByIndex("1");
//		
//		Assert.fail("END of testing#$D");
////		Thread.sleep(15000);
//		
//		
//		
//
//
//		dashboardpage.deleteLogoByIndex("16");
//		Thread.sleep(1000);	
////		Assert.assertTrue(true);
//		
//		
//		
//		WebElement logo = this.ldriver.findElement(By.xpath("//span[@class='css-1f3l2gp']/div[3]"));
//		ToolBox.waitFor(logo, 8); //the logos take some time to show up
//		logo.click();
//		
//		Thread.sleep(1000);
		
	}

}
