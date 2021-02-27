/**
 * 
 */
package utilities.auto;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.common.TestReporting.ExtentLogger;
import com.common.TestReporting.ExtentManager;
import com.common.TestReporting.ExtentReport;

/**
 * @author JoshZhuang
 *
 */
public class TestNGListener implements ITestListener {
   
	
	@Override
	public void onTestStart(ITestResult result) {	
		ExtentLogger.info("*** Test execution started *** --- "+result.getName());
		System.out.println("*** Test execution started *** --- "+result.getName());
	}
	
	@Override
	public void onStart(ITestContext result) {	
		
		try {
			ExtentLogger.info("*** RUN START *** --- "+result.getName());     // ----- this happens before init Extentreport. won't work	
		} catch (Exception e) {
			System.out.println("*** RUN START *** --- "+result.getName());
		} 		
	}
	
	@Override
	public void onFinish(ITestContext result) {	
		ExtentLogger.info("*** RUN FINISH *** --- "+result.getName());      // ---- this happens after Extentreport teardown. won't work
		System.out.println("*** RUN FINISH *** --- "+result.getName());  
	}


	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.syncReportResult();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentLogger.fail("*** Test failed due to failed steps or exception *** --- "+result.getName());  
		System.out.println("*** Test failed due to failed steps or exception *** --- "+result.getName()); 
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
