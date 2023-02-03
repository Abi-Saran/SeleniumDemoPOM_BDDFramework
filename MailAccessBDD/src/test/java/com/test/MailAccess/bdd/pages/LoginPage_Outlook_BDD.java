package com.test.MailAccess.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import  com.test.MailAccess.bdd.common.CommonLibrary_BDD;

public class LoginPage_Outlook_BDD extends CommonLibrary_BDD {
//	public WebDriver driver;
	
	public LoginPage_Outlook_BDD(WebDriver driver) {
//		super(driver);
		CommonLibrary_BDD.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="(//*[@class='internal sign-in-link'])[2]")
	public WebElement signInBtn;
	
	@FindBy(xpath="//*[@aria-label='Enter your email, phone, or Skype.']")
	public WebElement emailTxtField;
	
	@FindBy(css="#idSIButton9")
	public WebElement nextBtn;
	
	@FindBy(css="input[name='passwd'],input[type='password']")
	public WebElement pwdTxtField;
	
	@FindBy(css="#idSIButton9,input[value='Sign in']")
	public WebElement loginBtn;
	
	@FindBy(xpath="//*[@id='idBtn_Back']")
	public WebElement confirmBtn;

	@FindBy(xpath="//*[@style='' and @alt='SS']")
	public WebElement accManagerLink;
	
	@FindBy(how=How.CSS, using="#mectrl_currentAccount_secondary")
	public WebElement accName;
	
	@FindBy(xpath="//*[contains(text(),'Sign out')]")
	public WebElement signOutButton;
}
