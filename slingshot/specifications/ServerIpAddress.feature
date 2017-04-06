Feature: Successful and Unsuccessful Server IP address to the Consumer application
 As an user of the application
 I want to test Server IP address feature

Scenario: To Verify unsuccessful Server IP address to Consumer application with DATA ServerIp_Failure
         Given I am ON serverIpAddress page
         And I ENTER invalid details with DATA ServerIp_Failure
         When  I SUBMIT the form , error messages are displayed
         Then  I am ON serverIpAddress page
         
Scenario: To verify closing the IP Address pop-up in Conumer application
          Given I am ON serverIpAddress
          When  I CLICK closeIpbtn
          Then  I am ON home page
   
Scenario: To Verify successful Server IP address to Consumer application with DATA ServerIp_Success
         Given I am ON serverIpAddress page
         And  I ENTER valid details with DATA ServerIp_Success
         When  I SUBMIT the form
         Then   I am ON home page
   
   