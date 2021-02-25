/**
 * 
 */
package utilities.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author JoshZhuang
 *
 */
public class RmtDriverFactory {


		//constructor
		private RmtDriverFactory(){
			//do nothing here. Do NOT allow to init this class from outside.
		}
		
		
		private static RmtDriverFactory instance = new RmtDriverFactory(); // create DriverFactory instance
		
		
		public static RmtDriverFactory getInstance() {
			return instance;
		}
		
		
		ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); //treadlocal instance for webdriver - this is for multi-threading testing!!!!!
		
		
		public WebDriver getDriver() {   // call this method to get the driver object and launch browser
			return driver.get();        //this get() method gets current webdriver instance. this method is from ThreadLocal!!!		
		}
		
		
		public void setDriver(WebDriver driverParam) {   //call this to set driver object
			driver.set(driverParam);
		}
		
		
		public void removeDriver() {    //quit driver and close browser
			driver.get().quit();
			driver.remove();
		}
	
}
