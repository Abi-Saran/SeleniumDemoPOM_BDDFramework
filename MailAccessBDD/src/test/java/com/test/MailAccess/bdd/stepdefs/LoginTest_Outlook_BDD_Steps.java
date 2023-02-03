package com.test.MailAccess.bdd.stepdefs;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.test.MailAccess.bdd.common.CommonLibrary_BDD;
import com.test.MailAccess.bdd.pages.LoginPage_Outlook_BDD;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest_Outlook_BDD_Steps {
	public LoginPage_Outlook_BDD loginPage = null;
	public CommonLibrary_BDD common = null;
	
	public LoginTest_Outlook_BDD_Steps() {
		loginPage = new LoginPage_Outlook_BDD(CommonLibrary_BDD.driver);
		common = new CommonLibrary_BDD();
		CommonLibrary_BDD.log.info("initialized from LoginTest_Outlook_BDD_Steps...");
	}
	
	@And("^User clicks on Outlook SignIn button$")
	public void clickSignInBtn() {
		common.click(loginPage.signInBtn);
	}
	
	@Then ("^User enters Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void enterUserName(String userName, String password) throws FileNotFoundException, IOException, InterruptedException {
		common.clearAndEnterText(loginPage.emailTxtField, userName);
		CommonLibrary_BDD.log.info("Entered username - " + userName);
		common.wait(1);
		common.click(loginPage.nextBtn);
		common.clearAndEnterText(loginPage.pwdTxtField, password);
		CommonLibrary_BDD.log.info("Entered password - " + password);
		common.wait(1);
		common.click(loginPage.loginBtn);
		common.wait(1);
		common.click(loginPage.confirmBtn);
//		scenario.
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
	
	@When("^User clicks on Account Manager link$")
	public void clickAccManagerLink() throws InterruptedException {
		common.click(loginPage.accManagerLink);
		common.wait(1);
	}
	
	@Then("^User checks the account name as \"([^\"]*)\"$")
	public void verifyAccName(String expectedAccName) {
//		throw new SkipException("AccName comparing step skipped");
		common.compareText(expectedAccName, loginPage.accName);
	}
	
	@And("^User clicks on SignOut button$")
	public void clickSignOutBtn() throws InterruptedException {
		common.click(loginPage.signOutButton);
		common.wait(1);
	}

}
