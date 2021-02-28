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
 * this class stores all objects and methods from looka dashboard page
 */
public class DashboardPage {

	WebDriver driver;
	//locators	
	@FindBy(how=How.XPATH,using="//i[text()='menu']")
	WebElement menubutton;
	
	@FindBy(how=How.XPATH,using="//p[text()='Logo Generator']")
	WebElement logogenerator;
	
	@FindBy(how=How.XPATH,using="//h2[text()='Pick your industry']")
	WebElement industrylabel;
	
	
	//Create a class constructor for HomePage Class. this is for cross browser testing. e.g. passing "Chrome".
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

		
	
}
