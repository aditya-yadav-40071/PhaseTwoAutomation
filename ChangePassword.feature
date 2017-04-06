Feature: Successful and Unsuccessful ChangePassword for the Consumer application
		     As an user of the application
		     I want to test Change Password feature
		     
Background: SignIn:successfulSignIn

Scenario: To verify failure scenario to change password for Consumer Application with DATA ChangePassword_Failure
          Given I am ON dashboard page
          When I CLICK profileImageIcon , change password tab is displayed 
   		    Then I CLICK changePassword link 
		      Then I am ON changePassword page
		      And I ENTER invalid password details with DATA ChangePassword_Failure
		      When I SUBMIT the form and error message is displayed
		      Then I am ON changePassword page
@Group(1)
Scenario: To verify success scenario to change password for Consumer Application with DATA ChangePassword_Success
          Given I am ON dashboard page
          When I CLICK profileImageIcon change password tab is displayed
   		    Then I CLICK changePassword
		      Then I am ON changePassword page
		      And I ENTER valid password details with DATA ChangePassword_Success
		      When I SUBMIT the form and successful change password message is displayed
		      Then I am ON dashboard page
