/**
 * 
 */
package com.looka.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 *
 */
public class CommonLocators {

	WebDriver driver;
	
	//locators	
	@FindBy(how=How.XPATH,using="//i[text()='menu']")
	WebElement menubutton;
	
	@FindBy(how=How.XPATH,using="//span[@class='css-25shwt']")
	WebElement usernamelabel;
	
	@FindBy(how=How.XPATH,using="//li[text()='Log Out']")
	WebElement logout;
	
	@FindBy(how=How.XPATH,using="//p[text()='Logo Generator']")
	WebElement logogenerator;
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public CommonLocators(WebDriver driver) {
		this.driver = driver;
	}
	
	//check if user is logged in. return true or false
	public boolean checkUserLogin() throws Exception {	
		if (ToolBox.waitforObject(menubutton, 8)) {
			if (ToolBox.waitforObject(logout, 2)) {
				ExtentLogger.pass("user login successful!");
				return true;
			} else {
				ExtentLogger.failtestshot("user is NOT logged in!");
				return false;
			}
		}else {
				ExtentLogger.failtestshot("user is NOT logged in!");
				return false;
		}
				
	}
	
	
	//navigate to logogenerator
	public void navigateToGenerator() throws Exception {
		menubutton.click();
		Thread.sleep(1000);
		logogenerator.click();
		
//		if (ToolBox.waitforObject(industrylabel, 3)) {
//			ExtentLogger.pass("Landed on onboarding Page");
//		}else {
//				ExtentLogger.failtestshot("Pick industry label is NOT displayed!");
//		}
	}
	
}
