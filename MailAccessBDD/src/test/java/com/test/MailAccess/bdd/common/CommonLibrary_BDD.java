package com.test.MailAccess.bdd.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.LogManager;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;


public class CommonLibrary_BDD {
	public Properties properties = null;
	public FileInputStream fis = null;
	public static Logger log = null;
	public static WebDriver driver;
	public static SoftAssert softAssert;
	
	@Before
	public void setUp() throws IOException {
		PropertyConfigurator.configure("log4j.properties");
		log = Logger.getLogger(CommonLibrary_BDD.class);
//		new LoginTest_Outlook_BDD_Steps();
		log.info("Initialized...");
		softAssert = new SoftAssert();
//		launchBrowser();
	}
	
	public String getConfigProperty(String conigPropertyName) throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream("config.properties"));
		String configPropertyValue = properties.getProperty(conigPropertyName);
		return configPropertyValue;
	}
	
	@Given("^User launches browser$")
	public void launchBrowser() throws IOException {
//		setUp();
		log.info("Launching browser...");
		switch (getConfigProperty("browser").toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				LogManager.getLogManager().reset();
				driver = new ChromeDriver();
				log.info("Chrome browser launched...");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(getConfigProperty("implicitWait"))));
//				driver.get(getConfigProperty("url"));
				break;
				
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
				LogManager.getLogManager().reset();
				driver = new FirefoxDriver();
				log.info("Firefox browser launched...");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(getConfigProperty("implicitWait"))));
//				driver.get(getConfigProperty("url"));
				break;
		}
	}
	
	
	public String[] readExcelByRow(String sheetName, int rowNumber) throws FileNotFoundException, IOException {
		XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData.xlsx"));
		XSSFSheet sheet = workBook.getSheet(sheetName);
		
		int cols = sheet.getRow(rowNumber).getPhysicalNumberOfCells();
		String[] array = new String[cols];
		XSSFRow row = sheet.getRow(rowNumber);
		for (int c = 0; c < cols; c++) {
			XSSFCell cell = row.getCell(c);
			switch (cell.getCellType()) {
			case STRING:
				array[c] = cell.getStringCellValue();
				break;
				
			case NUMERIC:
				array[c] = String.valueOf(cell.getNumericCellValue());
				break;
				
			default:
				break;
			}
		}
		return array;
	}
	
	public boolean isElementDisplayed(WebElement element) {
		log.info("Checking the element " + element + " is displayed or not...");
		if (element.isDisplayed()) {
			log.info("Element " + element + " displayed");
			return true;
		} 
		log.info("Element " + element + " NOT displayed");
		return false;
	}
	


//  @When("^User navigates to \"(.*)\" page$")
//   				or
//  @When("^User navigates to "(.)" page$")
//   				or
//  @When("^User navigates to "(.+)" page$")
//					or
//	@When("^User navigates to {string} page$")
//					or
//  			like below
	@When("^User navigates to \"([^\"]*)\" page$")
	public void launchUrl(String url) {
		log.info("Navigating to the Url - " + url + "...");
		driver.get(url);
	}
	
	public boolean isElementEnabled(WebElement element) {
		log.info("Checking the element " + element + " is enabled or not...");
		if (element.isEnabled()) {
			log.info("Element " + element + " enabled");
			return true;
		} 
		log.info("Element " + element + " NOT enabled");
		return false;
	}
	
	public void clearAndEnterText(WebElement element, String txtToEnter) {
		log.info("Clearing the element " + element + " and entering the text...");
		element.clear();
		element.sendKeys(txtToEnter);
		log.info("Text entered...");
	}
	
	public String getPageTitle() {
		log.info("Getting the page title...");
		log.info("Page title - " + driver.getTitle());
		return driver.getTitle();
	}
	
	@Then("^User checks the page title as \"([^\"]*)\"$")
	public void checkPageTitle(String expectedTitle) {
//		softAssert = new SoftAssert();
		log.info("Expected page title - " + expectedTitle);
		log.info("Actual page title - " + driver.getTitle());
		if (StringUtils.equals(driver.getTitle(), expectedTitle)) {
			log.info("Page title is as expected.");
		} else {
			log.info("Page title is Not as expected");
			Assert.assertTrue(false);
		}
	}
	
//	public void quit() {
//		driver.quit();
//	}
	
	public void click(WebElement element) {
		log.info("Clicking the element " + element + "...");
		element.click();
	}
	
	public void wait(int seconds) throws InterruptedException {
		log.info("Waiting for " + seconds + " second(s)...");
		Thread.sleep(1000 * seconds);
	}
	
	public String getText(WebElement element) {
		log.info("Retrieving the text from the element " + element + "...");
		return element.getText();
	}
	
	public void compareText(String actualTxt, WebElement element) {
//		softAssert = new SoftAssert();
		log.info("Comparing texts...");
		System.out.println("Actual Text: " + actualTxt);
		System.out.println("Expected Text: " + element.getText());
		if (StringUtils.equals(actualTxt, element.getText())) {
			log.info("Texts are equal");
		} else {
			log.info("Texts are Not equal");
			Assert.assertTrue(false);
		}
	}
	
	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumeric() {
		return RandomStringUtils.randomNumeric(5);
	}
	
	public void closeDriver() {
		driver.close();
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario) throws WebDriverException, IOException, InterruptedException {
		if (scenario.isFailed()) {
//			final byte[] screenshot = FileUtils.readFileToByteArray(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
////			ExtentCucumberAdapter.addTestStepScreenCaptureFromPath("data:image/png;base64," + Base64.getEncoder().encodeToString(screenshot));
//			scenario.attach(screenshot, "data:image/png;base64," + Base64.getEncoder().encodeToString(screenshot), "image");
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Thread.sleep(500);
		scenario.attach(screenshot, "image/png", "Screenshot:");
		}
	}
	
	@After
	public void tearDown() {
		log.info("Quitting browser...");
		driver.quit();
	}

}
