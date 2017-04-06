Feature: Successful and Unsuccessful Course details for the Consumer application
		     As an user of the application
		     I want to test Course Detail feature
		     
Background:SignIn:successfulSignIn,Dashboard:clickCourse

Scenario: To Verify the name of the course clicked for Site Consumer Application with DATA CourseName_Success
          Given I am ON courseDetail page
          When I VERIFY courseName with DATA CourseName_Success
          Then I am ON courseDetail page
   
Scenario: To Verify successful start date of the added course in the Site Consumer Application with DATA StartDate_Success
          Given I am ON courseDetail page
          When I VERIFY startDateVerify with DATA StartDate_Success
          Then I am ON courseDetail page
          
Scenario: To Verify successful end date of the added course in the Site Consumer Application with DATA EndDate_Success
          Given I am ON courseDetail page
          When I VERIFY endDateVerify with DATA EndDate_Success
          Then I am ON courseDetail page
 
@Name(startClass)         
Scenario: To click on the Start/Continue button in Consumer application
          Given I am ON courseDetail page
          When I CLICK startContinue button 
          Then I am ON startClass page
          
