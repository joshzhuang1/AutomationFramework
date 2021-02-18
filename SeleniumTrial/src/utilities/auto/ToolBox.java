/**
 * 
 */
package utilities.auto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.TestReporting.ExtentLogger;

/**
 * @author JoshZhuang
 *
 */
public class ToolBox {
	
	WebElement webelemnt;
	
		//constructor
		public ToolBox(){
			//do nothing here. Do NOT allow to init this class from outside.
		}
		
		
		
		//check if object exists. return true or false
		public static boolean objectExists(WebElement webelement) {
			    try {
					webelement.isDisplayed();
						System.out.println("object does exist.");
						return true;
				} catch (Exception e) {
					System.out.println("object does NOT exist.");
					return false;
				}
		        	
		    }	
		
		
		//explicit wait for a webelement to show up within x seconds,if not shown, print info.
		public static void driverwaitforObject(WebElement webelement, int sec) {
			WebDriver driver = DriverFactory.getInstance().getDriver();
			WebDriverWait wait = new WebDriverWait(driver, sec); // create new WebDriverWait object. maximum time 5s 
			//below Wait max. sec seconds for the webelement to show (condition = visibilityOf webelement). If not throw error.
			try {
				wait.until(ExpectedConditions.visibilityOf(webelement));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ExtentLogger.warning("Webelement is NOT displayed. Waiting time "+sec+" seconds."); //if not show, print to ext report
			}
		}
	}


