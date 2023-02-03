package com.test.MailAccess.bdd.stepdefs;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.test.MailAccess.bdd.common.CommonLibrary_BDD;
import com.test.MailAccess.bdd.pages.LoginPage_Yahoo_BDD;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest_Yahoo_BDD_Steps  {
	
	public LoginPage_Yahoo_BDD loginPage = null;
	public CommonLibrary_BDD common = null;
	
	public LoginTest_Yahoo_BDD_Steps() {
		loginPage = new LoginPage_Yahoo_BDD(CommonLibrary_BDD.driver);
		common = new CommonLibrary_BDD();
		CommonLibrary_BDD.log.info("initialized from LoginTest_Yahoo_BDD_Steps...");
	}
	
	@And("^User clicks on Yahoo SignIn button$")
	public void clickSignInBtn() {
		common.click(loginPage.signInBtn);
	}
	
	@Then ("^User enters Yahoo Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void enterUserName(String userName, String password) throws FileNotFoundException, IOException, InterruptedException {
		common.clearAndEnterText(loginPage.userNameTxtField, userName);
		CommonLibrary_BDD.log.info("Entered username - " + userName);
		common.wait(1);
		common.click(loginPage.nextBtn);
		common.clearAndEnterText(loginPage.pwdTxtField, password);
		CommonLibrary_BDD.log.info("Entered password - " + password);
		common.wait(1);
		common.click(loginPage.signInNxtBtn);
		common.wait(1);
	}
	
	/**
	 * For Data Table with multiple test data - using Map<>
	 * 
	 * This method should contain all the remaining steps of the scenario until the end, otherwise this method wont work
	 */
//	@Then ("^User enters Username and Password$")
//	public void enterUserName(DataTable credentials) throws FileNotFoundException, IOException, InterruptedException {
//		for (Map<String, String> data : credentials.asMaps(String.class, String.class)) {
//			common.clearAndEnterText(loginPage.emailTxtField, data.get("Username"));
//			common.wait(1);
//			common.click(loginPage.nextBtn);
//			common.clearAndEnterText(loginPage.pwdTxtField, data.get("Password"));
//			common.wait(1);
//			common.click(loginPage.loginBtn);
//			common.wait(1);
//			common.click(loginPage.confirmBtn);
//		}
//	}
	
	@When("^User clicks on Yahoo mail Account Manager link$")
	public void clickAccManagerLink() throws InterruptedException {
		common.click(loginPage.accManageLink);
		common.wait(1);
	}
	
	@Then("^User checks the Yahoo mail account name as \"([^\"]*)\"$")
	public void verifyAccName(String expectedAccName) {
//		throw new SkipException("AccName comparing step skipped");
		common.compareText(expectedAccName, loginPage.accNameLink);
	}
	
	@And("^User clicks on Yahoo mail SignOut button$")
	public void clickSignOutBtn() throws InterruptedException {
		common.click(loginPage.signoutLink);
		common.wait(1);
//		CommonLibrary_BDD.softAssert.assertAll();
	}

}
