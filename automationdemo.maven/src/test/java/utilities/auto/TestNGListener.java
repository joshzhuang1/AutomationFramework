/**
 * 
 */
package utilities.auto;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.common.TestReporting.ExtentLogger;
import com.common.TestReporting.ExtentReport;

/**
 * @author JoshZhuang
 *
 */
public class TestNGListener implements ITestListener {
   
	
	@Override
	public void onTestStart(ITestResult result) {	
		ExtentLogger.info("###Test case Started### --- "+result.getName());
		System.out.println("###Test case Started### --- "+result.getName());
	}
	
	@Override
	public void onStart(ITestContext result) {	
		
		try {
			ExtentLogger.info("###Execution Started### --- "+result.getName());     // ----- this happens before init Extentreport. won't work	
		} catch (Exception e) {
			System.out.println("###Execution Started### --- "+result.getName());
		} 		
	}
	
	@Override
	public void onFinish(ITestContext result) {	
		ExtentLogger.info("###Test Finished### --- "+result.getName());      // ---- this happens after Extentreport teardown. won't work
		System.out.println("###Test Finished### --- "+result.getName());  
	}
	
//	public void onException(Throwable throwable, WebDriver driver) {
//		ExtentLogger.fail("###Exception thrown!!!### --- ");  
//		// do stuff
//	}

	
}
