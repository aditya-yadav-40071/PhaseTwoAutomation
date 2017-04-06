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
           
Scenario: To verify unsuccessful Sign In with old password for Consumer Application with DATA SignIn_With_Change_Pass_Failure
           Given I am ON home page
           When I CLICK signIn button
           Then I am ON signIn page
           And I ENTER invalid credentials with DATA SignIn_With_Old_Pass_Failure
           When I SUBMIT the form , error messages are displayed
           Then I am on dashboard page
		     
		     
		     