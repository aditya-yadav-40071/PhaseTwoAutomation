Feature: Successful and Unsuccessful individual registration of an individual to the application
  As an user of the application
  I want to test individual registration feature

  Scenario: To Verify unsuccessful individual registration to application with DATA IndividualRegister_Failure
    Given I am ON home page
    And I CLICK resgisterFromHome
    Then I am ON bifurcateUser page
    When I CLICK individualRegister link
    And I CLICK nextBtn
    Then I am ON individaulRegister page
    And I ENTER invalid details
    When I SUBMIT the form
    Then I am ON individaulRegister page
    And error messages are displayed
