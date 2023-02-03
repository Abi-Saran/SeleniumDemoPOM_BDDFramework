package com.test.MailAccess.bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		glue = {"com/test/MailAccess/bdd/common","com/test/MailAccess/bdd/stepdefs"},
		features = {"src/test/resources/features"},
		plugin = {"pretty", "html:target/index.html",
					"json:target/cucumber-reports/cucumber.json",
//					"junit:target/cucumber-reports/cucumber.xml",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
//					"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"
					},
		tags = "@outlook",
		monochrome = true,
		dryRun = false,
		publish = false
		) 

/**
 * 
 * ref: https://www.toolsqa.com/extent-report/extent-report-for-cucumber-testng-project/
 *
 */


public class CucumberRunner extends AbstractTestNGCucumberTests {
//	static CommonLibrary_BDD common = null;
//	
//	@BeforeClass
//	public static void setUp() throws IOException {
//		common = new CommonLibrary_BDD();
//		common.setUp();
//	}
//	
//	
//	
//	@AfterClass
//	public static void tearDown() {
//		common.tearDown();
//	}

}
