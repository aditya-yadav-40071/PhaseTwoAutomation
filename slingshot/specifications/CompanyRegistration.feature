Feature: Successful and Unsuccessful registration for Organization to the application  
  As an user of the application
  I want to test registration feature 
  
Scenario: To Verify unsuccessful registration to application with DATA Organisation_Reg_Failure
		  Given I am ON home page
		  When I CLICK registerFrmPage
		  Then I am ON bifurcateUser page
		  When I CLICK oganizationLnk
		  And I CLICK nextBtn
		  Then I am ON companyRegistration page
		  And I ENTER invalid details with DATA Organisation_Reg_Failure
		  And I SUBMIT the form
		  Then error messages are displayed