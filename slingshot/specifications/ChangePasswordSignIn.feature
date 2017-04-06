Feature: Successful Sign In with Changed Password for the Consumer application
		     As an user of the application
		     I want to test Sign In with Changed Password feature
           		     
Scenario: To verify successful Sign In with changed password for Consumer Application with DATA SignIn_With_Change_Pass_Success
           Given I am ON home page
           When I CLICK signIn button
           Then I am ON signIn page
           And I ENTER valid credentials with DATA SignIn_With_Change_Pass_Success
           When I SUBMIT the form
           Then I am on dashboard page
          When I CLICK profileImageIcon change password tab is displayed
   		    Then I CLICK changePassword
		      Then I am ON changePassword page
		      And I ENTER valid password details with DATA UpdatePassword_Success
		      When I SUBMIT the form and successful change password message is displayed
		      Then I am ON dashboard page
           

		     
		     
		     