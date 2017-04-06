Feature: Successful and Unsuccessful Change password functionality to the application  
  As an user of the application
  I want to test Change Password feature
  
Background: Login:loginSuccess

Scenario: To Verify the page title for change password
	Given I am ON dashboard page
	When I CLICK leftMenuButton 
	And I CLICK changePassword link
	Then I am ON changePassword page
	

Scenario: To verify the invalid scenarios for change password with DATA ChangePassword_Failure
	Given I am ON dashboard page
	When I CLICK leftMenuButton 
	And I CLICK changePassword link
	Then I am ON changePassword page
	And I ENTER invalid password details
	Then I SUBMIT the form
	And the error messages are displayed
	
Scenario: To verify the valid scenario for change password with DATA ChangePassword_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton 
	And I CLICK changePassword link
	Then I am ON changePassword page
	And I ENTER valid password details
	Then I SUBMIT the form
	Then I am ON dashboard page
	