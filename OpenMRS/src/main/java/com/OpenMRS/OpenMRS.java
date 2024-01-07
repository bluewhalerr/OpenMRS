package com.OpenMRS;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class OpenMRS extends BaseM {

	public static WebDriver driver;
	public static Logger logger = Logger.getLogger("org.openqa.selenium");;


	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "(//ul[@id='sessionLocation']/li)[2]")
	private WebElement locations;
	
	@FindBy(id = "loginButton")
	private WebElement loginBtn;
	
	@FindBy(linkText = "Register a patient")
	private WebElement registerPatient;
	
	@FindBy(name ="givenName")
	private WebElement givenName;
	
	@FindBy(name="familyName")
	private WebElement familyName;
	
	@FindBy(id = "next-button")
	private WebElement nextBtn;
	
	@FindBy(name = "gender")
	private WebElement gender;
	
	@FindBy(name = "birthdateDay")
	private WebElement birthdateDay;
	
	@FindBy(name = "birthdateMonth")
	private WebElement birthdateMonth;
	
	
	@FindBy(name = "birthdateYear")
	private WebElement birthdateYear;
	

	@FindBy(id = "address1")
	private WebElement address1;
	
	@FindBy(id = "address2")
	private WebElement address2;
	
	@FindBy(id = "cityVillage")
	private WebElement cityVillage;
	
	@FindBy(id = "stateProvince")
	private WebElement stateProvince;
	
	@FindBy(id = "country")
	private WebElement country;
	
	@FindBy(id = "postalCode")
	private WebElement postalCode;
	
	@FindBy(name = "phoneNumber")
	private WebElement phoneNumber;
	
	@FindBy(id = "relationship_type")
	private WebElement relationship_type;
	
	@FindBy(xpath = "//input[@placeholder='Person Name']")
	private WebElement personName;
	
	@FindBy(id = "submit")
	private WebElement submit;
	
	@FindBy(xpath = "//h3[text()='General Actions']/parent::ul/child::li")
	List<WebElement> startVisit;
	
	@FindBy(xpath = "//button[@id='start-visit-with-visittype-confirm']/parent::div/child::script")
	private WebElement popUpText;
	
	
	public OpenMRS(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	public  void loginAsAUser() {
		System.out.println("Verify whether user can able to login successfully");
		logger.log(Level.INFO, "Verify whether user can able to login successfully");

		try {
			String url = ConfigurationReader.getInstance().getProperty("url");
			getUrl(url);
			userInput(username, "Admin");
			userInput(password, "Admin123");
			eclick(locations);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		} catch (Exception e) {
			//logger.log(Level.INFO, "unable To Login");
		}
		System.out.println("User sucessfully logined the application");
	}
	
	public void registerPatint() throws Throwable {
		System.out.println("Verify whether user can able to register patient ");
		//logger.log(Level.INFO,"Verify whether user can able to register patient");
		try {
			eclick(registerPatient);
			userInput(givenName, "Jack");
			printValue(givenName);
			userInput(familyName, "Jack");
			printValue(givenName);
			eclick(nextBtn);
			eclick(gender);
			eclick(nextBtn);
			userInput(birthdateDay, "20");
			printValue(birthdateDay);
			dropDown(birthdateMonth, "by visibletext", "February");
			printValue(birthdateMonth);
			userInput(birthdateYear, "2000");
			printValue(birthdateYear);
			userInput(address1, "711-2880");
			printValue(address1);
			userInput(address2, "Nulla St.");
			printValue(address2);
			userInput(cityVillage, "Mississippi");
			printValue(cityVillage);
			userInput(stateProvince, "Mississippi");
			printValue(stateProvince);
			userInput(country, "USA");
			printValue(country);
			userInput(postalCode, "7401");
			printValue(postalCode);
			eclick(nextBtn);
			userInput(phoneNumber, "(257) 563-7401");
			printValue(phoneNumber);
			eclick(nextBtn);
			dropDown(relationship_type, "by visibletext", "Parent");
			userInput(personName, "Danny");
			printValue(personName);
			eclick(nextBtn);
			eclick(submit);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
			
		} catch (Exception e) {
			logger.log(Level.INFO, "Unable register patient");
		}
		System.out.println("User sucessfully registered ");
	}
	

	public void patientDashboardPage() {
		System.out.println("Verify whether user can able to enter patientDashboardPage");
		logger.log(Level.INFO,"Verify whether user can able to enter patientDashboardPage");
		try {
			eClick(startVisit);
			assertWebText("Are you sure you want to start a visit for Jack Jack now?",popUpText);
			
		} catch (Exception e) {
			logger.log(Level.INFO, "Unable enter patientDashboardPage");
		}
		System.out.println("User sucessfully entered the patientDashboardPage");
	}
}
