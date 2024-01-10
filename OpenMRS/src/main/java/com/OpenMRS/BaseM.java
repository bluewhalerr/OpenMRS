package com.OpenMRS;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseM {

	public static WebDriver driver;
	
	 public static void setDriver(WebDriver webDriver) {
	        driver = webDriver;
	    }
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
	public static WebDriver browserSetup(String browser) throws Exception {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				// options.addArguments("enable-automation");
				options.addArguments("--incognito");
				// options.addArguments("--start-maximized");
				// options.addArguments("--disable-extensions");
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

			} else if (browser.equalsIgnoreCase("firefox")) {
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
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
	public static void dropDown(WebElement element, String Option, String value) {
		Select sc = new Select(element);
		try {
			if (Option.equalsIgnoreCase("byIndex")) {
				int parseInt = Integer.parseInt(value);// Value is by default 'String' in the webpage so it is converted
														// into the 'Integer'
				sc.selectByIndex(parseInt);
			} else if (Option.equalsIgnoreCase("byValue")) {
				sc.selectByValue(value);
			} else if (Option.equalsIgnoreCase("byVisibleText")) {
				sc.selectByVisibleText(value);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("invalid select");
		}
		WebElement selectedOption = sc.getFirstSelectedOption();
		String selectedText = selectedOption.getText();
		System.out.println("Selected Option: " + selectedText);
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

	public static WebDriver assertWebText(String expectedText, WebElement element) {
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

	public void robotFileUpload(String filepath) throws AWTException {
		Robot r = new Robot();
		StringSelection str = new StringSelection(filepath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	//Method creation to take screenshot
	public static WebDriver takeScreenshot(String fileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Screenshots/"+ fileName + ".png");
		FileUtils.copyFile(source, destination);
		return driver;
	}	
	// System.out.println("Current URL: " + driver.getCurrentUrl());
}









