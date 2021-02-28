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
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	public void checkSavedLogo(String logoid) {
		ToolBox.waitforObject(savedlogosheading, 5); //wait for abit until the page is fully loaded
		
		WebElement logo = this.driver.findElement(By.xpath("//a[@href='/editor/"+logoid+"']"));
		if (ToolBox.waitforObject(logo, 5)) {
			ExtentLogger.pass("Saved logo is found: ID = "+logoid);
		}else {
			ExtentLogger.failshot("Unable to find saved logo! ID = "+logoid);
		}
	}
	
}
