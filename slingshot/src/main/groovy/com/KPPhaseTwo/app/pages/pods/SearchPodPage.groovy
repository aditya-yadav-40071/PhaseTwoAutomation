package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Samir  */

final class SearchPodPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new SearchPodForm().populateFields(browser, formData);
	}

	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in SearchPodPage")
		new SearchPodForm().submit(browser, formData)
	}

	//To verify that the pod list is displayed on clicking search button
	def static podsDisplayed = { browser, formData ->
		new SearchPodForm().podsDisplayed browser, formData
	}

	//To verify that the primary searched level and industry are displaying in the filters too
	def static filtersWithSameSearch = { browser, formData ->
		new SearchPodForm().filtersWithSameSearch browser, formData
	}

	//To verify that the result displayed for the serached pods are shown correctly
	def static correctPodsDisplayed = { browser, formData ->
		new SearchPodForm().correctPodsDisplayed browser, formData
	}

	//To verify that the pod name displayed in the pod details is same as the pod clicked
	def static podName = { browser, formData ->
		new SearchPodForm().podName browser, formData
	}

	//To add the first pod to wishlist
	def static addToWishlist = { browser, formData ->
		new SearchPodForm().addToWishlist browser, formData
	}

	//To verify that the wishlisted pod is displayed in wishlist page
	def static wishlistPodDsplyd = { browser, formData ->
		new SearchPodForm().wishlistPodDsplyd browser, formData
	}

	//To remove the first pod to wishlist
	def static removeFrmWshList = { browser, formData ->
		new SearchPodForm().removeFrmWshList browser, formData
	}

	//To verify that the wishlisted pod is removed from the pod wishlist page
	def static wishListedPodRemoved = { browser, formData ->
		new SearchPodForm().wishListedPodRemoved browser, formData
	}
	
	//To verify result shown per page is the same number pods displayed per page
	def static resultDisplayedPerPage = { browser, formData ->
		new SearchPodForm().resultDisplayedPerPage browser, formData
	}

	static final class SearchPodForm extends WebForm {

		//SearchPod form elements
		private static final def SEARCH_TEXT = ".//*[@id='courseSearch']"

		private static final def LEVEL = ".//md-select[@placeholder='Select Level']"

		private static final def INDUSTRY = ".//md-select[@placeholder='Select Industry']"

		private static final def SEARCH = "//button[@class='full-width button-primary']"

		private static final def POD_LIST = "//a[@class='content-name job-title pointer title-name blue capitalize ng-binding']"

		private static final def LEVEL_LIST = ".//*[@ng-value='level']"

		private static final def INDUSTRY_LIST = ".//md-option[@ng-value='ind.industryId']"

		private static final def FILTER_INDUSTRY = ".//md-select[@ng-model='filter.industry']"

		private static final def FILTER_LEVEL = ".//md-select[@ng-model='filter.level']"

		private static final def FILTER_INDUSTRY_LIST = ".//*[@ng-value='opt.industryId']"

		private static final def FILTER_LEVEL_LIST = ".//*[@ng-value='opt']"

		private static final def ELEMENT = "html/body/div[1]/div/div[2]/div/div/h4"

		private static final def FIFTY_RESULT = ".//*[@id='item_label50']"

		private static final def OVERVIEW = ".//a[@class='select individual-tab']"

		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/a[4]"

		private static final def POD_NAME = "html/body/div[1]/div[2]/div[2]/div[1]/h3"

		private static final def ADD_TO_WISHLIST = "//div[@ng-hide='isListEmpty']/div[2]/div[3]/div/span"

		private static final def FIRST_POD_NAME = "//div[@ng-hide='isListEmpty']/div[2]/div[1]/div/div/h3"

		private static final def WISHLISTED_POD = "//div[2]/div[1]/div/div/h3/a"

		private static final def ALL_WISHLISTED_POD = "//a[@class='content-name job-title pointer title-name blue ng-binding']"

		private static final def FIELDS = [LEVEL, INDUSTRY, SEARCH_TEXT]// the error fields.
		
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("SearchPodForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SEARCH_TEXT)){
						browser.populateField(FIELDS[i],formData[i])
					}
					if(FIELDS[i].equals(LEVEL)){
						browser.click LEVEL
						browser.delay(1000)
						def levelList = []
						if(formData[i].contains(",")){
							def data = formData[i].split(",")
							for(int j = 0; j < data.length; j++){
								levelList.add(data[j])
								KPCommonPage.selectAutoComplete(browser, LEVEL_LIST, data[j])
							}
						}else{
							levelList.add(formData[i])
							KPCommonPage.selectAutoComplete(browser, LEVEL_LIST , formData[i])
						}
						browser.click(ELEMENT)
						KPCommonPage.levelLists = levelList
					}
					if(FIELDS[i].equals(INDUSTRY)){
						browser.click INDUSTRY
						browser.delay(1000)
						def industryList = []
						if(formData[i].contains(",")){
							def data = formData[i].split(",")
							for(int k = 0; k < data.length; k++){
								industryList.add(data[k])
								KPCommonPage.selectAutoComplete(browser, INDUSTRY_LIST , data[k])
							}
						}else{
							industryList.add(formData[i])
							KPCommonPage.selectAutoComplete(browser, INDUSTRY_LIST , formData[i])
						}
						KPCommonPage.industryLists = industryList
						browser.click(ELEMENT)
					}
					browser.delay(1000)
				}
			}
			return outcome
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, SEARCH, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, searchPodPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(3500)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		//To verify that the pod list is displayed on clicking search button
		def static final podsDisplayed = { browser, formData ->
			if(browser.isDisplayed(POD_LIST)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsNotDisplayedIssue", "Pods are not displayed on search")
			}
		}

		//To verify that the primary searched level and industry are displaying in the filters too
		def static final filtersWithSameSearch = { browser, formData ->
			if(browser.isDisplayed(FILTER_INDUSTRY) && browser.isDisplayed(FILTER_LEVEL)){
				browser.click FILTER_INDUSTRY
				browser.delay(1000)
				def allIndustry = browser.getLists(FILTER_INDUSTRY_LIST)
				browser.click(ELEMENT)
				browser.delay(1000)
				browser.click FILTER_LEVEL
				browser.delay(1000)
				def allLevel = browser.getLists(FILTER_LEVEL_LIST)
				if(allIndustry?.sort(false) == KPCommonPage.industryLists?.sort(false) && allLevel?.sort(false) == KPCommonPage.levelLists?.sort(false)){
					KPCommonPage.industryLists = []
					KPCommonPage.levelLists = []
					return new SuccessOutcome()
				}else{
					KPCommonPage.industryLists = []
					KPCommonPage.levelLists = []
					return KPCommonPage.returnFailureOutcome(browser, "Industry&LevelIssue", "Industry and level searched does not match with the displayed option in Filters.")
				}
			}
		}

		//To verify that the result displayed for the serached pods are shown correctly
		def static final correctPodsDisplayed = { browser, formData ->
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
					browser.delay(1000)
					def allResult = browser.getLists(POD_LIST)
					def result = false
					outerloop:for(int i = 0; i < allResult.size(); i++){
						def allResultElement = browser.getListElements(POD_LIST)
						browser.scrollToElement(allResultElement[i])
						browser.delay(1000)
						browser.clickElement allResultElement[i]
						browser.delay(2000)
						if(browser.isDisplayed(OVERVIEW)){
							browser.click OVERVIEW
							browser.delay(1000)
							def levelAndIndustry = browser.getLists("//div[@class='row']/div/h5")
							def value = browser.getLists("//div[@class='row']/div[2]/span")
							for(int j = 0;j < levelAndIndustry.size();j++){
								if(levelAndIndustry[j].equals("Level") || levelAndIndustry[j].equals("Industry")){
									if(KPCommonPage.industryLists.contains(value[j]) || KPCommonPage.levelLists.contains(value[j])){
										println "KPCommonPage.industryLists "+KPCommonPage.industryLists+"\nvalue[j] "+value[j]+"\nKPCommonPage.levelLists  "+KPCommonPage.levelLists
										result = true
									}else{
										result = false
										break outerloop
									}
								}
							}
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
						}
						browser.click CLICK_PODS_BACK
						browser.delay(2000)
					}
					if(result){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "IndustryLevelNotMatchIssue", "Industry and Level does not match.")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
		}

		//To verify that the pod name displayed in the pod details is same as the pod clicked
		def static final podName = { browser, formData ->
			if(browser.isDisplayed(POD_NAME)){
				def podName = browser.gettext(POD_NAME)
				println "podName "+podName+ "\nKPCommonPage.podName "+KPCommonPage.podName
				if(podName.equalsIgnoreCase(KPCommonPage.podName)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PodNameMatchIssue", "Pod name does not match the selected pod")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodNameDisplayIssue", "Pod name is not displayed.")
			}
		}

		//To add the first pod to wishlist
		def static final addToWishlist = { browser, formData ->
			if(browser.isDisplayed(ADD_TO_WISHLIST)){
				KPCommonPage.firstPodName = browser.gettext(FIRST_POD_NAME)
				if(browser.gettext(ADD_TO_WISHLIST).contains("Add to wishlist")){
					browser.click ADD_TO_WISHLIST
					return new SuccessOutcome()
				}else{
					println "The pod is already wishlisted."
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Add to wishlist is not displayed.")
			}
		}

		//To verify that the wishlisted pod is displayed in wishlist page
		def static final wishlistPodDsplyd = { browser, formData ->
			if(browser.isDisplayed(WISHLISTED_POD)){
				def name = browser.gettext(WISHLISTED_POD)
				//println "name : "+name+"\nKPCommonPage.firstPodName : "+KPCommonPage.firstPodName
				if(KPCommonPage.firstPodName.equalsIgnoreCase(name)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "WishlistedPodMatchIssue", "Wishlisted pod is not same in the wishlist page.")
				}

			}else{
				return KPCommonPage.returnFailureOutcome(browser, "WishlistDisplayIssue", "Wishlisted pod is not displayed in the wishlist page.")
			}
		}

		//To remove the first pod to wishlist
		def static final removeFrmWshList = { browser, formData ->
			if(browser.isDisplayed(ADD_TO_WISHLIST)){
				//KPCommonPage.firstPodName = browser.gettext(FIRST_POD_NAME)
				if(browser.gettext(ADD_TO_WISHLIST).contains("Remove from wishlist")){
					browser.click ADD_TO_WISHLIST
					return new SuccessOutcome()
				}else{
					println "The pod is already not wishlisted."
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from wishlist is not displayed.")
			}
		}

		//To verify that the wishlisted pod is removed from the pod wishlist page
		def static final wishListedPodRemoved = { browser, formData ->
			if(browser.isDisplayed(WISHLISTED_POD)){
				def name = browser.getLists(ALL_WISHLISTED_POD)
				def result = false
				for(int i = 0; i < name.size(); i++){
					if(KPCommonPage.firstPodName.equalsIgnoreCase(name[i])){
						result = false
						break
					}else{
						result = true
					}
				}
				if(result){
					println "The pod is removed from wishlisted page."
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from wishlist is not displayed.")
				}
			}else{
				println "No pod is displayed so the pod removed from wishlist is also not displaying."
				return new SuccessOutcome()
			}
		}
		
		//To verify result shown per page is the same number pods displayed per page
		def static final resultDisplayedPerPage = { browser, formData ->
			if(browser.isDisplayed(POD_LIST)){
				def totalPodPerPage = browser.getLists(POD_LIST)
				println "totalPodPerPage : "+totalPodPerPage.size()
				def totalResult = browser.gettext("//p[@class='clearfix content output-count-header ng-binding']").split("-")[1].subSequence(0, 2).trim()
				println "total Result"+totalResult
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ResultPerPageDisplayIssue", "Result for per page does not match the displayed result.")
			}
		}
	}
	
	/*static final class FilterPodForm extends WebForm {
		
	}*/
}
