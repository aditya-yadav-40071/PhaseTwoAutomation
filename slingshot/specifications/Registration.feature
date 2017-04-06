Feature: Successful and Unsuccessful registration to the application  
  As an user of the application
  I want to test registration feature 
  
Scenario: To Verify unsuccessful registration to application with DATA Registration_Failure
		  Given I am ON Home page
		  When I CLICK login link
		  Then I am ON signIn popup
		  When I CLICK register link
		  Then I am ON registration page