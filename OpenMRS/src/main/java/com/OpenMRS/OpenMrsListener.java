package com.OpenMRS;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class OpenMrsListener implements ITestListener {

	public static WebDriver driver;

	public void onTestStart(ITestResult result) {
		System.out.println(result.getName() + ": Test has started");
	}

	public void onTestFinish(ITestResult result) {
		System.out.println(result.getName() + ": Test has finished");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed :" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		Date currentDate = new Date();
		String timeStamp = dateFormat.format(currentDate);
		String fileName = result.getName() + "_" + timeStamp;
		System.out.println("Test Failed :" + result.getName());
		try {
			driver= BaseM.takeScreenshot(fileName);
			System.out.println("Successfully captured a screenshot: " + fileName + ".png");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped :" + result.getName());
	}
}
