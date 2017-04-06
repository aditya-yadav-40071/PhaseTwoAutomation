package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage



final class DashboardPage extends WebPage {

	//To logout from the application
	def static logOut = { browser, formData ->
		new DashboardForm().logOut browser, formData
	}

	def static ifLoggedInAsTrainer= { browser, formData ->
		new DashboardForm().ifLoggedInAsTrainer browser, formData
	}

	def static ifLoggedInAsJobAdmin= { browser, formData ->
		new DashboardForm().ifLoggedInAsJobAdmin browser, formData
	}

	def static ifLoggedInAsSubAdmin= { browser, formData ->
		new DashboardForm().ifLoggedInAsSubAdmin browser, formData
	}


	static final class DashboardForm extends WebForm {

		//Manage Admins Page elements
		private static final def  LEFT_MENU = "//button[@aria-expanded='false'][@role='button']"

		private static final def LOG_OUT = ".//a[@ng-click='logOut()']"

		private static final def ADMIN_TAB = ".//li[@class='pull-left selected']/a"

		private static final def MENU_ITEMS = ".//li[@class='menu-name-default mb30']/a"

		private static final def SUB_ADMIN_TAB = ".//ul[@class='pull-left menu mt_40 no-padding ng-scope']/li/a"

		//Verify that added admin is displayed in the list
		def static final logOut = { browser, formData ->
			browser.delay(1500)
			browser.scrollToElement(browser.getElement(Browser.XPATH, LEFT_MENU))
			browser.delay(1500)
			browser.click LEFT_MENU
			browser.delay(1500)
			if(LOG_OUT !=null){
				browser.scrollToElement(browser.getElement(Browser.XPATH, LOG_OUT))
				browser.click LOG_OUT
				browser.delay(3000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "LogOutIssue", "Not able to logout from the application")
			}
		}

		def static final ifLoggedInAsTrainer = { browser, formData ->
			if(browser.isDisplayed(ADMIN_TAB) && browser.gettext(ADMIN_TAB).equalsIgnoreCase("Train")){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "TrainerTabIssue", "The Trainer tab is not displayed")
			}
		}

		def static final ifLoggedInAsJobAdmin = { browser, formData ->
			if(browser.isDisplayed(ADMIN_TAB) && browser.gettext(ADMIN_TAB).equalsIgnoreCase("Hire")){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobAdminTabIssue", "The Job Admin tab is not displayed")
			}
		}

		def static final ifLoggedInAsSubAdmin = { browser, formData ->
			def tabText= browser.getLists(SUB_ADMIN_TAB)
			println "tabText::"+tabText
			def checkResult= browser.isDisplayed(SUB_ADMIN_TAB)
			println "checkResult::"+checkResult
			if(checkResult && (tabText.contains("Train") && tabText.contains("Hire"))){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SubAdminTabIssue", "The Sub Admin tab is not displayed")
			}
		}
	}
}

