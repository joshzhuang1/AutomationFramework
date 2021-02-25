/**
 * 
 */
package utilities.auto;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author JoshZhuang
 *
 */
public class RmtBrowserFactory {
	static final String huburlstring = "http://localhost:4546/wd/hub";
	static WebDriver driver;
	
	//init a threadlocal browser instance ---- this also caters for multi-threading. MAGIC.
	public static WebDriver initBrowser(String browserName) throws IOException {
		driver = createBrowserInstance(browserName);      // create browser instance based on browser name
		
		RmtDriverFactory.getInstance().setDriver(driver);    //to set browser instance as threadlocal for multi-threading
		driver = RmtDriverFactory.getInstance().getDriver();  //now driver becomes "threadlocal instance", so that multi-threading is possible		
		return driver;										// return driver
	}
	
	
	//create remotewebdriver instance
	public static WebDriver createBrowserInstance(String browserName) throws IOException {
		DesiredCapabilities cap =  new DesiredCapabilities();
		
		if (browserName.equalsIgnoreCase("firefox")) {	
			cap.setBrowserName("firefox");		
		}
		
		else if (browserName.equalsIgnoreCase("chrome")) {			
			cap.setBrowserName("chrome"); 		
		}
		
		else if (browserName.equalsIgnoreCase("edge")) {
			cap.setBrowserName("edge");	
		}
		
//		cap.setPlatform(Platform.WINDOWS); //specify platform 
		URL huburl = new URL(huburlstring); //selenium hub address
		
		//start browser instance on remotewebdriver
		driver = new RemoteWebDriver(huburl,cap); 
		
		return driver;
	}
	
	
	//navigate to url
	public static void launchURL(String url) {
		driver.manage().window().maximize(); //maximise window
		driver.get(url); //navigate to *url*
	}

	
}
