package com.OpenMRS;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseM {

	public static WebDriver driver;

	// Method Creation to launch the browser-Using External Drivers
	public WebDriver browserLaunch(String browser) throws Exception {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + ".\\src\\test\\resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + ".\\src\\test\\resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.geko.driver",
						System.getProperty("user.dir") + ".\\src\\test\\resources\\Drivers\\msedgedriver.exe");
				driver = new FirefoxDriver();
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Browser name is not valid");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}

	// Method Creation to launch the browser-Using WebDriverManager
	public WebDriver browserSetup(String browser) throws Exception {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				//options.addArguments("enable-automation");
				options.addArguments("--incognito");
				// options.addArguments("--start-maximized");
				//options.addArguments("--disable-extensions");
				driver = WebDriverManager.chromedriver().capabilities(options).create();
				System.out.println("Browser Started:" + browser);

			} else if (browser.equalsIgnoreCase("firefox")) {
				driver = WebDriverManager.firefoxdriver().create();
				System.out.println("Browser Started:" + browser);
				FirefoxProfile profile = new FirefoxProfile();
				FirefoxOptions optionsFirefox = new FirefoxOptions();
				profile.setPreference("extensions.enabled", false);
				optionsFirefox.setProfile(profile);

			} else if (browser.equalsIgnoreCase("edge")) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--disable-features=msEdgeLocalDataServices");
				driver = WebDriverManager.edgedriver().capabilities(options).create();
				System.out.println("Browser Started:" + browser);

			} else {
				throw new Exception("Browser is not correct");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Browser name is not valid");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	
	public static WebDriver launchBrowser(String browser) throws Throwable {

		try {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("incognito");
				driver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("gecko")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("invalid browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	// Method creation to WAIT-untilElementVisible
	public static void waituntilElementVisibility(int timeDuration, WebElement element) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
			wb.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("NoSuchElementException");
			throw new RuntimeException();
		}
	}

	// Method Creation to get URL
	public static WebDriver getUrl(String url) {
		driver.get(url);
		return driver;
	}

	// Method Creation to sendKeys
	public static void userInput(WebElement element, String value) {
		// waituntilElementVisibility(30, element);
		element.sendKeys(value);
	}

	// Method Creation to click on webElement
	public static void eclick(WebElement element) {
		element.click();

	}
	// Method Creation to click on webElement
		public static void eClick(List<WebElement> element) {
			((WebElement) element).click();
		}
		
	// Method Creation to getText of webElement
	public static void printValue(WebElement element) {
		// waituntilElementVisibility(30, element);
		String text = element.getText();
		System.out.println(text);
	}

	// Method Creation to perform mouse action
	public static void mouseAction(WebElement element, String options) throws Throwable {
		Actions a = new Actions(driver);
		try {
			if (options.equalsIgnoreCase("click")) {
				waituntilElementVisibility(30, element);
				a.click(element).perform();
			} else if (options.equalsIgnoreCase("context click")) {
				waituntilElementVisibility(30, element);
				a.contextClick(element).perform();
			} else if (options.equalsIgnoreCase("double click")) {
				waituntilElementVisibility(30, element);
				a.doubleClick(element).perform();
			} else if (options.equalsIgnoreCase("move to element")) {
				waituntilElementVisibility(30, element);
				a.moveToElement(element).perform();
				a.click().perform();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("invalid mouse action");
		}
	}

	// Method Creation to verify webEElt displayed
	public static void display(WebElement element) {
		boolean d = element.isDisplayed();
		System.out.println(d);
	}

	// Method Creation to select element form Dropdown
	public static void dropDown(WebElement element, String value, String options) throws Throwable {
		Select s = new Select(element);
		try {
			if (options.equalsIgnoreCase("by index")) {
				// string integer convert
				int p = Integer.parseInt(value);
				s.selectByIndex(p);

			} else if (options.equalsIgnoreCase("by value")) {
				s.selectByValue(value);
			} else if (options.equalsIgnoreCase("by visibletext")) {
				s.selectByVisibleText(value);
			} else {
				throw new Exception();

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("invalid select");

		}
	}

	// Method creation to get the Title of the WebPage
	public static WebDriver assertPageTitle(String expectedTitle) {
		try {
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			System.out.println("Actual Title :" + actualTitle + " & " + "Expected Title :" + expectedTitle);
			System.out.println("***ASSERTION PASSED***");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("***ASSERTION FAILED***");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	public static WebDriver assertWebText(String expectedText,WebElement element) {
		try {
			String actualText = element.getText();
			SoftAssert sf = new SoftAssert();
			sf.assertEquals(actualText, expectedText);
			sf.assertAll();
			System.out.println("Actual Text :" + actualText + " & " + "Expected Text :" + expectedText);
			System.out.println("***ASSERTION PASSED***");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("***ASSERTION FAILED***");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}
	
	//System.out.println("Current URL: " + driver.getCurrentUrl());
}
