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
 * this class stores all objects and methods from looka homepage
 */
public class HomePage {

	WebDriver driver;
	//locators
	@FindBy(how=How.XPATH,using="//a[text()='Log in']")
	WebElement loginlabel;
	
	@FindBy(how=How.ID,using="login-email")
	WebElement usernamefield;
	
	@FindBy(how=How.ID,using="login-password")
	WebElement passwordfield;
	
	@FindBy(how=How.XPATH,using="//button[contains(text(),'Sign In')]")
	WebElement signinbutton;
	
	@FindBy(how=How.XPATH,using="//p[text()='Incorrect password']")
	WebElement wrongpwlabel;
	
	@FindBy(how=How.XPATH,using="//i[text()='menu']")
	WebElement menubutton;
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//login
	public void login(String username,String password) throws Exception {
		loginlabel.click();
		Thread.sleep(1000);
		usernamefield.sendKeys(username);
		passwordfield.sendKeys(password);
		Thread.sleep(1000);
		signinbutton.click();
		
		if (ToolBox.waitforObject(menubutton, 8)) {
			ExtentLogger.pass("login successfully! "+username+" : ***************");
		}else {
				ExtentLogger.failtestshot("login failed! see screenshot! "+username+" : ***************");
		}
				
	}
	
	
}
