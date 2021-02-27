/**
 * 
 */
package com.looka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utilities.auto.ToolBox;

/**
 * @author JoshZhuang
 * this class stores all objects and methods from looka onboarding page
 */


public class OnboardingPage {

	WebDriver driver;
	//locators	
	@FindBy(how=How.XPATH,using="//i[text()='menu']")
	WebElement menubutton;
	
	@FindBy(how=How.XPATH,using="//input[@id='industry']")
	WebElement industryfield;	
	
	@FindBy(how=How.XPATH,using="//div[@class='css-of0vry css-1smao10 css-k008qs css-1jkp9i7']")
	WebElement logosyoulikeparent;
	
	@FindBy(how=How.XPATH,using="//button[text()='Continue']")
	WebElement continuebutton;
	
	@FindBy(how=How.XPATH,using="//button[text()='Skip']")
	WebElement skipbutton;
	
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public OnboardingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//this is to return a webelement - from "Pick some logos you like" section. pass 1, 2, 3,...etc
//	public WebElement getLikedLogo(String Index){	
//		return this.driver.findElement(By.xpath("//div[@class='css-of0vry css-1smao10 css-k008qs css-1jkp9i7']/div["+Index+"]"));
//		}
	
	//to wait until the indexed logo to show up from "Pick some logos you like" section, and select it 
	public void selectLikedLogo(String Index) {
		WebElement logo = this.driver.findElement(By.xpath("//div[@class='css-of0vry css-1smao10 css-k008qs css-1jkp9i7']/div["+Index+"]"));
		ToolBox.waitforObject(logo, 5); //the logos take some time to show up
		logo.click();
	}
	
	//generate a logo using wizard
	public void generateLogo() throws Exception {
		industryfield.sendKeys("IT Consulting");
		continuebutton.click();
		Thread.sleep(5000);

		selectLikedLogo("3");
		selectLikedLogo("5");
//
		
		
		Thread.sleep(6000);
		
	}
	
	
	
	
}
