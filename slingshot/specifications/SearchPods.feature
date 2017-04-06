Feature: Successful and Unsuccessful Search Pods functionality to the application  
  As an user of the application
  I want to test Search Pods feature
  
Background: Login:loginSuccess

Scenario: To verify the Search Pod landing page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	
Scenario: To verify that clicking on search button displays all the pods with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodButton
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	
Scenario: To verify the industry and Level selected in primary pods only is displayed in filters with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I ENTER details for search with DATA PodSearch_Success
	Then I SUBMIT the form
	And I VERIFY filtersWithSameSearch
	Then the filters displayed have the same data as selected
	
Scenario: To verify the result displayed for search pods is correct with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I ENTER details for search with DATA PodSearch_Success
	Then I SUBMIT the form
	And I VERIFY correctPodsDisplayed
	Then the correct search pod result is displayed
	
Scenario: To verify that clicking on pods navigate to pod details page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodButton
	Then the list is displayed
	When I CLICK firstPod
	Then I am ON podDetails page
	
Scenario: To verify the name of pod after navigating to pod details page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodButton
	And I CLICK firstPod
	Then I am ON podDetails page
	When I VERIFY podName
	Then the pod name is same as the first pod clicked
	
Scenario: To verify clicking on Pod Wishlist from left menu navigates to Pod wishlist page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	
Scenario: To wishlist a pod and verify the pod is displaying in the wishlist page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodButton
	And I PERFORM addToWishlist to add the first pod to wishlist
	And I CLICK dashBoardLink to navigate to dashboard page
	Then I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	And I VERIFY wishlistPodDsplyd in wishlist page
	Then the wishlisted pod is displayed in the wishlist page
	
Scenario: To remove a wishlist and verify that the wwishlist has been removed with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	When I CLICK searchPodButton
	And I PERFORM addToWishlist to add the first pod to wishlist
	And I PERFORM removeFrmWshList to remove the first pod to wishlist
	And I CLICK dashBoardLink to navigate to dashboard page
	Then I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	And I VERIFY wishListedPodRemoved
	Then the wishlisted pod is removed from the page
	

Scenario: To verify the number of pods displayed in each page is matching the number shown with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	When I CLICK searchPodButton
	Then I am ON searchPods page
	And I VERIFY resultDisplayedPerPage
	Then the result shown per page is the same number pods displayed per page
	