Feature: Click on the Start/Continue button for the Consumer application
		     As an user of the application
		     I want to test the clicking the Start/Continue button feature
		     
Background:SignIn:successfulSignIn,Dashboard:clickCourse,CourseDetail:startClass

Scenario: To click on the exit button to exit from the class room of a particular course in Consumer application
         Given I am ON startClass page
         When I CLICK exit button 
         Then I am ON courseDetail page
         
Scenario: To verify the breadcrumb name with video name of the course in Consumer application    
				 Given I am ON startClass page
         When I VERIFY courseVideoName 
         Then I am ON startClass page
         
Scenario: To click the next video and verify the breadcrumb name with video name of the course in Consumer application    
				 Given I am ON startClass page
         When I CLICK nextbtn   
         Then I VERIFY courseVideoName with breadcrumb name
         When I CLICK backbtn 
         Then I am ON startClass page
         And I VERIFY courseVideoName with breadcrumb name
         Then I am ON startClass page