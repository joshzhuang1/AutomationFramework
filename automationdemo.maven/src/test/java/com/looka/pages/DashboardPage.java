/**
 * 
 */
package com.looka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.common.TestReporting.ExtentLogger;

import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 * this class stores all objects and methods from looka dashboard page
 */
public class DashboardPage {

	WebDriver driver;
	//locators	
	@FindBy(how=How.XPATH,using="//h2[text()='Saved Logos']")
	WebElement savedlogosheading;
	
	@FindBy(how=How.XPATH,using="//button[@class='css-emh0r7']")
	WebElement loadingicon;
	//button[@class="css-emh0r7"]
	
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	
	public void checkSavedLogo(String logoid) {
		ToolBox.waitforObject(savedlogosheading, 5);
		try {
			WebElement logo = this.driver.findElement(By.xpath("//a[@href='/editor/"+logoid+"']")); //if this webelement doesn't exist, exception will be thrown.
			ExtentLogger.passshot("Saved logo is found. ID = "+logoid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExtentLogger.failshot("Unable to find saved logo! ID = "+logoid);
		}
	}
	
}
