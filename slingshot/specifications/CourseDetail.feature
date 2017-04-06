Feature: Successful and Unsuccessful Course details for the Consumer application
		     As an user of the application
		     I want to test Course Detail feature
		     
Background:SignIn:successfulSignIn,Dashboard:clickCourse

Scenario: To Verify the name of the course clicked for Site Consumer Application with DATA CourseName_Success
          Given I am ON courseDetail page
          When I VERIFY courseName 
          Then I am ON courseDetail page

Scenario: To verify the the course completion statement of the course clicked by the consumer with DATA Completion_Statement_Success
          Given I am ON courseDetail page
          When I VERIFY courseCompletionStatement with DATA Completion_Statement_Success
          Then I am ON courseDetail page   

Scenario: To Verify successful Course Detail of the added course in the Site Consumer Application with DATA Course_Detail_Success
          Given I am ON courseDetail page
          When I VERIFY courseDetails with DATA Course_Detail_Success
          Then I am ON courseDetail page          
   
@Name(startClass)         
Scenario: To click on the Start/Continue button in Consumer application
          Given I am ON courseDetail page
          When I CLICK startContinue button 
          Then I am ON startClass page
          
