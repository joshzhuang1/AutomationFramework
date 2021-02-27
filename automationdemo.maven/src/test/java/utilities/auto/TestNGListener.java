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
		ExtentLogger.info("*** Test case execution started *** --- "+result.getName());
		System.out.println("*** Test case execution started *** --- "+result.getName());
	}
	
	@Override
	public void onStart(ITestContext result) {	
		
		try {
			ExtentLogger.info("*** Run Started *** --- "+result.getName());     // ----- this happens before init Extentreport. won't work	
		} catch (Exception e) {
			System.out.println("*** Run Started *** --- "+result.getName());
		} 		
	}
	
	@Override
	public void onFinish(ITestContext result) {	
		ExtentLogger.info("*** Test Completed *** --- "+result.getName());      // ---- this happens after Extentreport teardown. won't work
		System.out.println("*** Test Completed *** --- "+result.getName());  
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentLogger.fail("*** Test failed due to failed validation or exception *** --- "+result.getName());  
		System.out.println("*** Test failed due to failed validation or exception *** --- "+result.getName()); 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
}
