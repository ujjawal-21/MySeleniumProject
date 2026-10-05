package tests;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerActivity implements ITestListener{
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Starting: "+result.getName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Success: "+result.getName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Failed: "+result.getName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Skipped: "+result.getName());
	}

	
}
