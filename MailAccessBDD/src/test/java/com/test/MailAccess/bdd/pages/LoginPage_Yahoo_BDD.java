package com.test.MailAccess.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_Yahoo_BDD extends BasePage {
//	public WebDriver driver;
	
	public LoginPage_Yahoo_BDD(WebDriver driver) {
		super(driver);
//		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.CSS,using=".fuji-button-link.fuji-button-inverted.signin")
	public WebElement signInBtn;
	
	@FindBy(how=How.CSS,using="#login-username")
	public WebElement userNameTxtField;
	
	@FindBy(how=How.XPATH,using="//input[@id='login-signin']")
	public WebElement nextBtn;
	
	@FindBy(how=How.CSS,using="#login-passwd")
	public WebElement pwdTxtField;
	
	@FindBy(how=How.CSS,using="#login-signin")
	public WebElement signInNxtBtn;

	@FindBy(how=How.CSS,using="label[for=ybarAccountMenu]")
	public WebElement accManageLink;
	
	@FindBy(how=How.CSS,using="._yb_1k6lr._yb_16w6l._yb_jxpk0")
	public WebElement accNameLink;
	
	@FindBy(how=How.XPATH,using="//*[@data-soa='Sign out of all']")
	public WebElement signoutLink;
}
