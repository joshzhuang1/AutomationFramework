/**
 * 
 */
package com.looka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	@FindBy(how=How.XPATH,using="//button[text()='Confirm']")
	WebElement confirmdelete;
	
	
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
	
	
	//delete logo by id
	public void deleteLogoByID(String logoid) throws Exception {
		
		ExtentLogger.info("Try to find and delete logo ID = "+logoid);
		WebElement idlabel;
		
		try {
			idlabel = this.driver.findElement(By.xpath("//a[@href='/editor/"+logoid+"']"));
		} catch (Exception e) {
			ExtentLogger.failshot ("Logo ID = "+logoid+" is NOT found! Unable to delete!");
			e.printStackTrace();
			return;
		}
		
		//mouse hover over
		Actions action = new Actions(driver); //init Actions class
		action.moveToElement(idlabel).perform(); //Find the element and hover over
		Thread.sleep(2000);
		WebElement deletebutton = this.driver.findElement(By.xpath("//a[@href='/editor/"+logoid+"']/../button[@class='css-1psw9c1']"));
		deletebutton.click();
		Thread.sleep(1000);
		confirmdelete.click();
		Thread.sleep(4000);
		
		//check if the webelement disappeared
		if (ToolBox.waitforObject(idlabel, 2)) {
			ExtentLogger.failshot("Did NOT delete! Logo still displays! ID = "+logoid);			
		}else {
			ExtentLogger.pass("Logo is successfully deleted! ID = "+logoid);
		}
			
	}
	
	
	//delete logo by index. first find id by index, and then call deleteLogoByID
	public void deleteLogoByIndex(String index) throws Exception {
		
		ExtentLogger.info("Try to find and delete logo by Index = "+index);
//		WebElement indexeddelete;
		WebElement indexededit;
		
		try {
//			indexeddelete = this.driver.findElement(By.xpath("//span[@class='css-1f3l2gp']/div["+index+"]/div[@class='css-hua7oi css-k008qs']/button[@class='css-1psw9c1']"));
			indexededit = this.driver.findElement(By.xpath("//span[@class='css-1f3l2gp']/div["+index+"]/div[@class='css-hua7oi css-k008qs']/a[text()='Edit']"));
		} catch (Exception e) {
			ExtentLogger.failshot ("Logo index = "+index+" does NOT exist! Unable to delete!");
			e.printStackTrace();
			return;
		}
		
		//get href value by xpath of the indexededit(edit button), which contains logo id.
		String href = indexededit.getAttribute("href");
		String logoid = href.replaceAll("\\D+",""); //remove all non-digits value
		ExtentLogger.info("Logo ID = "+logoid+" is found for Index = "+index);
		
		//call deleteLogoByID
		deleteLogoByID(logoid);
		
	}
	
	
	public void waitingLoadingIcon(int sec) {
		ToolBox.waitforObject(loadingicon, 8);
		ToolBox.waitforDisappear(loadingicon, sec);
	}
	
	
	
	
}
