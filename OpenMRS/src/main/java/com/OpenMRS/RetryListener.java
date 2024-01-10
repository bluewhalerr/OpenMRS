package com.OpenMRS;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

	int retryCount = 0;
	int maxRetryCount = 3;

	public boolean retry(ITestResult result) {

		if (!result.isSuccess()) {
			if (retryCount < maxRetryCount) { // Check if the maximum number of test execution is reached
				System.out.println("Retrying Test : Re-running " + result.getName() + " for " + (retryCount + 1) + " time(s)."); // Print number of  Retry attempts
	
				retryCount++; // Increase the maxRetryCount by 1

				result.setStatus(ITestResult.FAILURE); // Mark test as failed
				return true; // Rerun the failed test
			} else {
				result.setStatus(ITestResult.FAILURE); // TestNG marks last run as failed, if last run is max retry
			}
		} else {
			result.setStatus(ITestResult.SUCCESS); // TestNG parks test as passed when the test test passes

		}

		return false;
	}
}
