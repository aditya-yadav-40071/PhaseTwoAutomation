package com.KPPhaseTwo.app.dsl

import com.KPPhaseTwo.app.pages.pods.SearchPodPage
/**
 * Created by Samir on 21/02/2017
 */
class PodManager {
	
	//To verify that the pod list is displayed on clicking search button
	def podsDisplayed = { browser, formData ->
		SearchPodPage.podsDisplayed browser, formData
	}

	//To verify that the primary searched level and industry are displaying in the filters too
	def filtersWithSameSearch = { browser, formData ->
		SearchPodPage.filtersWithSameSearch browser, formData
	}

	//To verify that the result displayed for the serached pods are shown correctly
	def correctPodsDisplayed = { browser, formData ->
		SearchPodPage.correctPodsDisplayed browser, formData
	}

	//To verify that the pod name displayed in the pod details is same as the pod clicked
	def podName = { browser, formData ->
		SearchPodPage.podName browser, formData
	}
	
	//To add the first pod to wishlist
	def addToWishlist = { browser, formData ->
		SearchPodPage.addToWishlist browser, formData
	}

	//To verify that the wishlisted pod is displayed in wishlist page
	def wishlistPodDsplyd = { browser, formData ->
		SearchPodPage.wishlistPodDsplyd browser, formData
	}
	
	//To remove the first pod to wishlist
	def removeFrmWshList = { browser, formData ->
		SearchPodPage.removeFrmWshList browser, formData
	}
	
	//To verify that the wishlisted pod is removed from the pod wishlist page
	def wishListedPodRemoved = { browser, formData ->
		SearchPodPage.wishListedPodRemoved browser, formData
	}
	
	//To verify result shown per page is the same number pods displayed per page
	def resultDisplayedPerPage = { browser, formData ->
		SearchPodPage.resultDisplayedPerPage browser, formData
	}
}
