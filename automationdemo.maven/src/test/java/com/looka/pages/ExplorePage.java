/**
 * 
 */
package com.looka.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
	
	
}
