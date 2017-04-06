Feature: To test all the feature of Post Job page
  As an user of the application
  I want to test various functionalities of Post Job page

Background: Login:loginSuccess
 
@Group(11) 
Scenario: To Verify page-title of 'Post Job' page
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   
@Group(12)
Scenario: To verfy the navigation from 'Post Job' page to dashboard page using breadcrumb
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   When I CLICK dashbreadcrumb 
   Then I am ON dashboard page
   
@Group(13)
Scenario: To verfy the navigation from 'Post Job' page to view all Job Postings page
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   And I CLICK viewAllPostings link
   Then I am ON jobPostList page 
   
@Group(14)
Scenario: To verfy the navigation from 'Post Job' page to view all Job Postings page
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   And I CLICK viewAllPostings link
   Then I am ON jobPostList page 
   When I CLICK postJobBreadcrumb
   Then I am ON postJob page
   