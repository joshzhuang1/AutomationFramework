/**
 * 
 */
package firstpackage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.common.TestReporting.ExtentReport;
import com.looka.pages.HomePage;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;

/**
 * @author JoshZhuang
 *
 */


public class ForDebugging {
	WebDriver ldriver;
	String reportfolder = "Lookatests";
	String browsername = "chrome";
	String url = "https://www.looka.com/";
	
	@AfterClass //close chrome and flush extreport
	public void closeApp() throws IOException {

		//		driver.quit();  //close browser
		DriverFactory.getInstance().removeDriver(); //close current threadlocal browser instance --- for multi-threading
		
		//finalise test report
		ExtentReport.flushReports(reportfolder);
	}

	@Test
	public void launchApp() throws Exception {
		ldriver = BrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
		ExtentReport.createTestReport(reportfolder, reportfolder); //init extent report
		
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
		
		HomePage homepage = PageFactory.initElements(ldriver, HomePage.class);
		
		homepage.login("joshzhuangdemo@gmail.com","K!e9R#cj4KRXQ7w");
		
		Thread.sleep(1000);	
		
	}

}
