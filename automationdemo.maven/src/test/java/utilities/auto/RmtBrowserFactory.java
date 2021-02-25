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

	static WebDriver driver;
	
	//init a threadlocal browser instance ---- this also caters for multi-threading. MAGIC.
	public static WebDriver initBrowser(String browserName) throws IOException {
		driver = createBrowserInstance(browserName);      // create browser instance based on browser name
		
		RmtDriverFactory.getInstance().setDriver(driver);    //to set browser instance as threadlocal for multi-threading
		driver = RmtDriverFactory.getInstance().getDriver();  //now driver becomes "threadlocal instance", so that multi-threading is possible		
		return driver;										// return driver
	}
	
	
	//pass browser name for browser instance creation
	public static WebDriver createBrowserInstance(String browserName) throws IOException {
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(); //launch firefox
		}
		
		else if (browserName.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//			driver = new ChromeDriver(); //launch chrome		
			DesiredCapabilities cap =  new DesiredCapabilities();
			cap.setBrowserName("chrome"); //specify browser. need to make sure chromedriver.exe is phisically on the node
//			cap.setPlatform(Platform.WINDOWS); //specify platform 
			URL huburl = new URL("http://localhost:4546/wd/hub"); //local hub address
			
			//Hub will see which node has chrome browser and launch there
			driver = new RemoteWebDriver(huburl,cap); //start browser instance on remote driver
			
		}
		
		else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
			driver = new EdgeDriver(); //launch edge
		}
			
		return driver;
	}
	
	
	//navigate to url
	public static void launchURL(String url) {
		driver.manage().window().maximize(); //maximise window
		driver.get(url); //navigate to *url*
	}

	
}
