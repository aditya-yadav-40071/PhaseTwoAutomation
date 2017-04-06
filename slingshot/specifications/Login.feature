Feature: Successful and Unsuccessful Login to the application  
  As an user of the application
  I want to test Login feature 
  
Scenario: To Verify closing the sign in pop up with DATA Login_Success
	Given I am ON home page
	When I CLICK login link
	Then I am ON login page
	  
Scenario: To verify the error messages with wrong login credentials with DATA Login_Failure
	Given I am ON home page
	When I CLICK login link
	Then I am ON login page
	And I ENTER invalid login details
	And I SUBMIT the form
	Then I am ON login page
	
@Name(loginSuccess)
Scenario: To verify the successful login to the application with DATA Login_Success
	Given I am ON home page
	When I CLICK login link
	Then I am ON login page
	And I ENTER valid login details
	And I SUBMIT the form
	Then I am ON dashboard page
	
Scenario: To verify the user after logging in with the credentials with DATA Login_Success
	Given I am ON home page
	When I CLICK login link
	Then I am ON login page
	And I ENTER valid login details
	And I SUBMIT the form
	Then I am ON dashboard page
	And I CLICK editProfile link
	Then I am ON editProfile page
	And I VERIFY correctUserLoggedIn
	Then the logged in user is the one displayed on dashboard
	
@Name(ForgotPassword)
Scenario: To Verify forgotPassword Link on Login Page
    Given I am ON home page
    And I CLICK loginFromHome
    Then I am ON login page
    When I CLICK forgotPassword link on the Page
    Then I am ON forgotPassword page

@Name(LoginAsAdmin)
Scenario: To login to the application as an admin with DATA LoginAsAdmin_Success
    Given I am ON home page
    And I CLICK loginFromHome
    Then I am ON login page
    And I ENTER the details
    When I SUBMIT the form
    Then I am ON dashboard page
	