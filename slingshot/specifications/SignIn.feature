Feature: Successful and Unsuccessful SignIn to the Consumer application
		     As an user of the application
		     I want to test SignIn feature
		  
Scenario: To Verify unsuccessful SignIn to Consumer application with DATA SignIn_Failure
		   Given I am ON home page
       When I CLICK signIn button
       Then I am ON signIn pop_up
       And I ENTER invalid credentials with DATA SignIn_Failure
       When I SUBMIT the form, error messages are displayed
       And I am ON signIn pop_up
       
@Name(successfulSignIn) 
Scenario: To verify successful SignIn for Consumer application with DATA SignIn_Success
       Given I am ON home page
       When I CLICK signIn button
       Then I am ON signIn pop_up
       And  I ENTER valid credentials with DATA SignIn_Success
       When I SUBMIT the form
       Then I am ON dashboard page
       
Scenario: To verify successful logout to Consumer application with DATA SignIn_Success
       Given I am ON home page
       When I CLICK signIn button
       Then I am ON signIn pop_up
       And I ENTER valid credentials with DATA SignIn_Success
       When I SUBMIT the form
       Then I am ON dashboard page
       When I CLICK profileImageIcon
       And I CLICK logout button
       Then I am ON home page
       
 Scenario: To verify closing the Sign In pop-up in Conumer application
       Given I am ON home page
       When I CLICK signIn button 
       Then I am ON signIn pop_up
       When I CLICK closeButton button 
       Then I am ON home page
       
Scenario: To Verify the username of the user who logs in to the Site Consumer application with DATA SignIn_Success
       Given I am ON home page
       When I CLICK signIn button
       Then I am ON signIn pop_up
       And I ENTER valid credentials with DATA SignIn_Success
       When I SUBMIT the form
       Then I am ON dashboard page
       And I VERIFY consumerName with DATA ConsumerName_Success
       Then I am ON dashboard page