package com.OpenMRS;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestRunnerEdge extends BaseM {
	public static WebDriver driver;
	public static OpenMRS openMRSTest;

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setUp(String browser) throws Throwable {
		driver = BaseM.browserSetup(browser);
	}

	@Test
	public void tc01() throws Throwable {

		openMRSTest = new OpenMRS(driver);
		openMRSTest.loginAsAUser();
	}

	@Test
	public void  tc02() throws Throwable {
		openMRSTest.registerPatint();
	}

	@Test
	public void tc03() {
		openMRSTest.patientDashboardPage();
	}

	@Test
	public void tc04() throws AWTException, InterruptedException, IOException {
		openMRSTest.fileAttachment();
	}

	@Test
	public void tc05() throws InterruptedException {
		openMRSTest.endVisit();
	}

	@Test
	public void  tc06() {
		openMRSTest.captureVitals();
	}

	@Test
	public void  tc07() throws InterruptedException {
		openMRSTest.mergePatientVisits();
	}
	
	@Test
	public void  tc08() throws Throwable {
		openMRSTest.deletePatient();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("Closing WebDriver in tearDown method");
		if (driver != null) {
			driver.quit();
		}
	}

}
