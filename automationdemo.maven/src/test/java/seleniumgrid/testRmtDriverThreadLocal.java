/**
 * 
 */
package seleniumgrid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import utilities.auto.BrowserFactory;
import utilities.auto.DriverFactory;
import utilities.auto.RmtBrowserFactory;
import utilities.auto.RmtDriverFactory;

/**
 * @author JoshZhuang
 *
 */
public class testRmtDriverThreadLocal {

	WebDriver ldriver;
	String browsername = "chrome";
	String url = "https://www.google.com.au";
	
	@Test
	public void test() throws Exception {
		
		ldriver = RmtBrowserFactory.initBrowser(browsername); //init threadlocal instance - recommended!
		Thread.sleep(2000);	
		
		ldriver.manage().window().maximize(); //maximise window
		ldriver.get(url); //navigate to *url*	
		ldriver.get("https://www.google.com/");
		System.out.println("Title is "+ldriver.getTitle());
		Thread.sleep(2000);
		RmtDriverFactory.getInstance().removeDriver();
	}
	
	
}
