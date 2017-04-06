Feature: To Verify End User Licence Aggrement of the Consumer in the Cloud Site Consumer application
		     As an user of the application
		     I want to test End User Licence Aggrement feature
		     
Scenario: To verify successful EULA for Consumer application with DATA SignIn_Success
       Given I am ON home page
       When I CLICK signIn button
       Then I am ON signIn pop_up
       And  I ENTER valid credentials with DATA SignIn_Success
       When I SUBMIT the form
       Then I am ON eula page
       When I CLICK agree checkbox
       Then I CLICK continue button
       And I am ON learningTrack page