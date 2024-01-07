package com.OpenMRS;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestRunner extends BaseM {

	public static WebDriver driver;
	public static OpenMRS openMRSTest;

	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void setUp(String browser ) throws Throwable {
//		String browser = ConfigurationReader.getInstance().getProperty("browser");
//		driver = BaseM.launchBrowser(browser);		
		driver = launchBrowser(browser);
		//driver = getUrl("https://qa-refapp.openmrs.org/openmrs/login.htm");
	}
	
//	@BeforeMethod
//	public void currentPage() {
//		
//	}
	@Test(description = "tc01-Login")
	public void tc01() {

		openMRSTest = new OpenMRS(driver);
		openMRSTest.loginAsAUser();
		assertPageTitle("Login");
	}

	@Test
	public void tc02() throws Throwable {
		openMRSTest.registerPatint();
		assertPageTitle("Login");
	}
	@Test
	public void tc03() {
		openMRSTest.patientDashboardPage();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("Closing WebDriver in tearDown method");
		if (driver != null) {
			driver.quit();
		}
	}

}
