package com.OpenMRS;

import java.awt.AWTException;
import java.io.IOException;
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

	@FindBy(name = "givenName")
	private WebElement givenName;

	@FindBy(name = "familyName")
	private WebElement familyName;

	@FindBy(id = "next-button")
	private WebElement nextBtn;

	@FindBy(xpath = "//option[text()='Male']")
	private WebElement gender;

	@FindBy(name = "birthdateDay")
	private WebElement birthdateDay;

	@FindBy(xpath = "//select[@name='birthdateMonth']")
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

	@FindBy(xpath = "//div[contains(text(),'Start Visit')]")
	private WebElement startVisit;

	@FindBy(xpath = "//button[@id='start-visit-with-visittype-confirm']/parent::div/p")
	private WebElement popUpText;

	@FindBy(xpath = "//div[@class='dialog-header']/following::div/child::script/following-sibling::button[text()='Confirm']")
	private WebElement confirm;

	@FindBy(xpath = "//div[@id='visit-details']/child::div/following::div//a[@id='attachments.attachments.visitActions.default']")
	private WebElement attach;

	@FindBy(id = "visit-documents-dropzone")
	private WebElement file;

	@FindBy(xpath = "//h3[text()='Caption']/following::textarea")
	private WebElement fileInput;

	@FindBy(xpath = "//button[text()='Upload file']")
	private WebElement uploadBtn;
	
	@FindBy(xpath = "//p[contains(@class,'att_date-time left')]")
	private WebElement uploadTime;

	@FindBy(xpath = "//span[@class='PersonName-givenName']")
	private WebElement nameAgain;

	@FindBy(xpath = "(//div[contains(text(),'End Visit')])[2]")
	private WebElement endVisit;

	@FindBy(xpath = "//div[@id='end-visit-dialog']//child::div/ul/li")
	private WebElement endConfirmMsg;

	@FindBy(xpath = "(//button[@class='confirm right'][text()='Yes'])[2]")
	private WebElement endConfirm;

	@FindBy(xpath = "//div[@id='visit-details']/child::div/following::div//a[@id='referenceapplication.realTime.vitals']")
	private WebElement captureVitals;

	@FindBy(xpath = "//span[@id='height']/input")
	private WebElement height;

	@FindBy(xpath = "//span[@id='weight']/input")
	private WebElement weight;

	@FindBy(xpath = "//span[@id='calculated-bmi']")
	private WebElement calculatedBmi;

	@FindBy(xpath = "//span[@id='temperature']/input")
	private WebElement temperature;

	@FindBy(id = "save-form")
	private WebElement saveForm;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement saveBtn;

	@FindBy(xpath = "//a[text()=' End Visit']")
	private WebElement endVisit1;

	@FindBy(xpath = "//div[@id='end-visit-dialog']/descendant::button[@class='confirm right']")
	private WebElement endVistConfirmBtn;

	@FindBy(xpath = "//a[@id='noVisitShowVisitCreationDialog']")
	private WebElement startAgain;

	@FindBy(xpath = "//div[contains(text(),'Merge Visit')]")
	private WebElement mergeVisit;

	@FindBy(xpath = "//input[@type='checkbox']")
	private List<WebElement> checkBox;

	@FindBy(id = "mergeVisitsBtn")
	private WebElement mergeBtn;

	@FindBy(xpath = "//input[@class='cancel']")
	private WebElement returnBtn;

	@FindBy(xpath = "//div[contains(text(),'Delete Patient')]")
	private WebElement deletePatient;

	@FindBy(xpath = "//p[@class='dialog-instructions'][contains(text(),'Are you')]")
	private WebElement deletePopup;

	@FindBy(xpath = "//input[@id='delete-reason']")
	private WebElement deleteReason;

	@FindBy(xpath = "(//input[@id='delete-reason']/following::button)[1]")
	private WebElement deleteConfirm;

	public OpenMRS(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void loginAsAUser() throws Exception {
		System.out.println("Verify whether user can able to login successfully");
		logger.log(Level.INFO, "Verify whether user can able to login successfully");
			String url = ConfigurationReader.getInstance().getProperty("url");
			getUrl(url);
			assertPageTitle("Login");
			String Username = ConfigurationReader.getInstance().getProperty("username");
			userInput(username, Username);
			String Password = ConfigurationReader.getInstance().getProperty("password");
			userInput(password, "Admin123");
			eclick(locations);
			eclick(loginBtn);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void registerPatint() throws Throwable {
		System.out.println("Verify whether user can able to register patient ");
		logger.log(Level.INFO, "Verify whether user can able to register patient");
			assertPageTitle("Home");
			eclick(registerPatient);
			assertPageTitle("OpenMRS Electronic Medical Record");
			userInput(givenName, "Jack");
			printValue(givenName);
			userInput(familyName, "Jack");
			printValue(familyName);
			eclick(nextBtn);
			eclick(gender);
			eclick(nextBtn);
			userInput(birthdateDay, "20");
			printValue(birthdateDay);
			dropDown(birthdateMonth, "byVisibleText", "February");
			userInput(birthdateYear, "2000");
			printValue(birthdateYear);
			eclick(nextBtn);
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
			dropDown(relationship_type, "byVisibleText", "Parent");
			userInput(personName, "Danny");
			printValue(personName);
			eclick(nextBtn);
			eclick(submit);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void patientDashboardPage() {
		System.out.println("Verify whether user can able to enter patientDashboardPage");
		logger.log(Level.INFO, "Verify whether user can able to enter patientDashboardPage");
			eclick(startVisit);
			waituntilElementVisibility(30, popUpText);
			assertWebText("Are you sure you want to start a visit for Jack Jack now?", popUpText);
			eclick(confirm);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void fileAttachment() throws AWTException, InterruptedException, IOException {
		System.out.println("Verify whether user can able to Attach the file");
		logger.log(Level.INFO, "Verify whether user can able to Attach the file");
			eclick(attach);
			eclick(file);
			String filePath = "C:\\Users\\user\\Downloads\\hawkins";
			Thread.sleep(3000);
			robotFileUpload(filePath);
			//Runtime.getRuntime().exec("src\\test\\resources\\autoIT\\Upload1.exe");
			Thread.sleep(6000);
			userInput(fileInput, "Captions");
			eclick(uploadBtn);
			Thread.sleep(3000);
			eclick(nameAgain);
			waituntilElementVisibility(60, uploadTime);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void endVisit() throws InterruptedException {
		System.out.println("Verify whether user can able to end PatientEndVisit");
		logger.log(Level.INFO, "Verify whether user can able to end PatientEndVisit");
		Thread.sleep(2000);
			eclick(endVisit);
			assertWebText("Are you sure you want to end this visit?", endConfirmMsg);
			eclick(endConfirm);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void captureVitals() {
		System.out.println("Verify whether user can able to capturePatientVitals");
		logger.log(Level.INFO, "Verify whether user can able to capturePatientVitals");
			eclick(startVisit);
			waituntilElementVisibility(30, popUpText);
			assertWebText("Are you sure you want to start a visit for Jack Jack now?", popUpText);
			eclick(confirm);
			eclick(captureVitals);
			userInput(height, "180");
			printValue(height);
			eclick(nextBtn);
			userInput(weight, "90");
			printValue(weight);
			eclick(nextBtn);
			printValue(calculatedBmi);
			eclick(nextBtn);
			userInput(temperature, "40");
			printValue(temperature);
			eclick(nextBtn);
			eclick(saveForm);
			eclick(saveBtn);
			eclick(endVisit1);
			eclick(endVistConfirmBtn);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void mergePatientVisits() throws InterruptedException {
		System.out.println("Verify whether user can able to mergePatientVisits ");
		logger.log(Level.INFO, "Verify whether user can able to mergePatientVisits ");
		eclick(startAgain);
		eclick(confirm);
		Thread.sleep(5000);
		eclick(nameAgain);
		eclick(mergeVisit);
		for (WebElement web : checkBox) {
			eclick(web);
		}
		eclick(mergeBtn);
		eclick(returnBtn);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	public void deletePatient() throws Throwable {
		System.out.println("Verify whether user can able to deletePatient");
		logger.log(Level.INFO, "Verify whether user can able to deletePatient ");
		eclick(deletePatient);
		assertWebText("Are you sure you want to DELETE the patient Jack Jack", deletePopup);
		userInput(deleteReason, "delete test");
		eclick(deleteConfirm);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
}
