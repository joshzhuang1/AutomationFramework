/**
 * 
 */
package utilities.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author JoshZhuang
 * will accept browser name parameter, hence launch corresponding browser as ThreadLocal instance. Parallel testing is supported.
 */
public class BrowserFactory {

	static WebDriver driver;
		
	//init a threadlocal browser instance ---- this also caters for multi-threading. MAGIC.
	public static WebDriver initBrowser(String browserName) {
		driver = createBrowserInstance(browserName);      // create browser instance based on browser name
		
		DriverFactory.getInstance().setDriver(driver);    //to set browser instance as threadlocal for multi-threading
		driver = DriverFactory.getInstance().getDriver();  //now driver becomes "threadlocal instance", so that multi-threading is possible		
		return driver;										// return driver
	}
	
	
	//pass browser name for browser instance creation
	public static WebDriver createBrowserInstance(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(); //launch firefox
		}
		
		else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver(); //launch chrome
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
	
	
	
	
	
	



