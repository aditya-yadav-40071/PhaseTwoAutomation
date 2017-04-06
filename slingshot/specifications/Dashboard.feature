Feature: To Verify and Click on the Courses enrolled to the Consumer in the Site Consumer application
		     As an user of the application
		     I want to test clicking the Courses enrolled to the Consumer feature
		     
Background: SignIn:successfulSignIn

Scenario: To Verify the courses enrolled to the consumer with DATA Courses_Name_Match_Success
          Given I am ON dashboard page
          When I VERIFY courseNameMatch with DATA Courses_Name_Match_Success
          Then I am ON dashboard page
          
@Name(clickCourse)
Scenario: To Verify successful courses click on the added course in the Site Consumer Application with DATA CourseName_Success
          Given I am ON dashboard page
          When I VERIFY clickCourseName with DATA CourseName_Success
          Then I am ON courseDetail page
		     
