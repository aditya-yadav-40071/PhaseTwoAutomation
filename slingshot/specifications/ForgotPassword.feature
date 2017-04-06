Feature: Successful and Unsuccessful Forgot Password to the Consumer application
		     As an user of the application
		     I want to test Forgot Password feature
		  
Scenario: To Verify unsuccessful Forgot Password to Consumer application with DATA ForgotPassword_Failure
       Given I am ON home page
       When  I CLICK signIn button
       Then  I am ON signIn pop-up
       When  I CLICK forgotPass link
       Then  I am ON forgotPass pop-up
       And   I ENTER invalid credentials
       When  I SUBMIT the form error messages are displayed
       Then  I am ON forgotPass pop-up   
       
Scenario: To Verify successful Forgot Password to Consumer application with DATA ForgotPassword_Success
       Given I am ON home page
       When  I CLICK signIn button
       Then  I am ON signIn pop-up
       When  I CLICK forgotPass link
       Then  I am ON forgotPass pop-up
       And   I ENTER valid credentials
       When  I SUBMIT the form success messages is displayed
       Then  I am ON home page   
       