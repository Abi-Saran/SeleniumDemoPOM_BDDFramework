@outlook
Feature: Login
#Background: Initial steps

Scenario Outline: Login with valid credentials
	Given User launches browser
	When User navigates to "https://outlook.live.com/owa/" page 
	And User clicks on Outlook SignIn button
	Then User enters Username as "<Username>" and Password as "<Password>"
#	And User enter Password and clicks SignIn button
	Then User checks the page title as "Mail - Saran S - Outlook"
	
#Scenario: Checking account name
	When User clicks on Account Manager link
	Then User checks the account name as "sa.saran@outlook.com"
	
#Scenario: Signout	
	And User clicks on SignOut button

	
	Examples:
	| Username | Password |
	| sa.sarvan@outlook.com | Demo@123 |
#	| s.saran.qa@outlook.com | Demo@123 |
	

		