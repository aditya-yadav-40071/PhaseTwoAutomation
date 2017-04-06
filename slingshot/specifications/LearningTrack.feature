Feature: To Verify and Click on the Learning Track enrolled to the Consumer in the Cloud Site Consumer application
		     As an user of the application
		     I want to test clicking the Learning Track enrolled to the Consumer feature
		     
Background:SignIn:successfulSignIn

Scenario: To Verify the learning tracks enrolled to the consumer with DATA Lt_Name_Match_Success
          Given I am ON learningTrack page
          When I VERIFY learningTrackEnrolled with DATA Lt_Name_Match_Success
          Then I am ON learningTrack page
          
@Name(clickLearningTrack)
Scenario: To Verify successful click on the added learning tracks and verify the number of courses in the Consumer Application with DATA Lt_Name_Click_Success
          Given I am ON learningTrack page
          When I PERFORM clickLearningTrack with DATA Lt_Name_Click_Success
          Then I am ON learningTrackDetail page