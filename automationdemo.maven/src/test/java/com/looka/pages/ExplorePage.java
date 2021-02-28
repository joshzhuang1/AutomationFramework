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
public class ExplorePage {

	WebDriver driver;
	//locators	
	@FindBy(how=How.XPATH,using="//h4[contains(text(),'generating some logos')]")
	WebElement generatinglogo;
	
	@FindBy(how=How.XPATH,using="//h4[contains(text(),'Pick a logo')]")
	WebElement picklogo;

		
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public ExplorePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void checkLogoGenerating(int sec) {
		if (ToolBox.waitforObject(generatinglogo, sec)) {
			ExtentLogger.info("Generating logos in progress");
		}else {
				ExtentLogger.failshot("Not generating logos! Check screenshot!");
		}
	}
	
	public void checkGeneratedLogos(int sec) {
		if (ToolBox.waitforObject(picklogo, sec)) {
			ExtentLogger.pass("Logos are generated as expected!");
		}else {
				ExtentLogger.failshot("Logos are not generated yet! Check screenshot!");
		}
	}
	
	
	
}
