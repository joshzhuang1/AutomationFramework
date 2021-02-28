/**
 * 
 */
package firstpackage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentReport;
import com.looka.pages.DashboardPage;
import com.looka.pages.LoginPage;
import com.looka.pages.OnboardingPage;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;

/**
 * @author JoshZhuang
 *
 */

@Listeners(utilities.auto.TestNGListener.class)

public class ForDebugging {
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
	public void addLogoToFaviourite() throws Exception {
		
		

//			String url = "https://looka.com/editor/62208209";
//			String logoid = url.replaceAll("\\D+",""); // this is to get rid of all non-digits. so "https://looka.com/editor/62208209" becomes "62208209"
			
	
		
		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
//		ExtentReport.createTestReport(reportfolder, reportfolder); //init extent report
		
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
		
		//init LoginPage
		LoginPage loginpage = PageFactory.initElements(ldriver, LoginPage.class);	
		
		//login
		loginpage.login("joshzhuangdemo@gmail.com","K!e9R#cj4KRXQ7w");
		
		Thread.sleep(2000);	
	}

}
