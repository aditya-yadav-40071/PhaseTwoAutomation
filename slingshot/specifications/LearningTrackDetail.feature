Feature: Successful and Unsuccessful Learning Track details for the Consumer application
       As an user of the application
       I want to test Course Detail feature

Background:SignIn:successfulSignIn,LearningTrack:clickLearningTrack

Scenario: To Verify the learning track name after clicking the learning track in the Consumer Application with DATA Lt_Name_Click_Success
          Given I am ON learningTrackDetail page
          When I VERIFY learningTrackName with DATA Lt_Name_Click_Success
          Then I am ON learningTrackDetail page

Scenario: To Verify the courses enrolled to the consumer present in the learning track with DATA Course_Name_Match_Success
          Given I am ON learningTrackDetail page
          When I VERIFY courseEnrolled with DATA Course_Name_Match_Success
          Then I am ON learningTrackDetail page
          
Scenario: To verify the the Learning Track completion statement of the Learning Track clicked by the consumer with DATA Lt_Completion_Statement_Success
          Given I am ON learningTrackDetail page
          When I VERIFY ltCompletionStatement with DATA Lt_Completion_Statement_Success
          Then I am ON learningTrackDetail page   
    
Scenario: To Verify successful Learning Track Details in the Consumer Application with DATA Learning_Track_Detail_Success
         Given I am ON learningTrackDetail page
         When I VERIFY learningTrackDetails with DATA Learning_Track_Detail_Success
         Then I am ON learningTrackDetail page

@Name(clickCourse)
Scenario: To Verify successful click on the course added in the learning track for the Consumer Application with DATA Click_Course_Name_Success
         Given I am ON learningTrackDetail page
         When I PERFORM clickCourse with DATA Click_Course_Name_Success
         Then I am ON courseDetail page
