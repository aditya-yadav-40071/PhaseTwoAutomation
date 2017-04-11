package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import java.awt.Component.BaselineResizeBehavior
import com.KPPhaseTwo.app.pages.KPCommonPage



final class ManageAdminsPage extends WebPage {

	//Verify that added admin is displayed in the list
	def static addedAdminDisplayed = { browser, formData ->
		new ManageAdminsForm().addedAdminDisplayed browser, formData
	}

	//to remove an admon from the admins list
	def static removeAnAdmin = { browser, formData ->
		new ManageAdminsForm().removeAnAdmin browser, formData
	}

	//Verify that admin has been removed from the admins list
	def static isAdminRemoved = { browser, formData ->
		new ManageAdminsForm().isAdminRemoved browser, formData
	}

	//To click on the Admin name to view the profile
	def static clickOnAdminName = { browser, formData ->
		new ManageAdminsForm().clickOnAdminName browser,formData
	}

	def static  adminDetails = { browser, formData ->
		new ManageAdminsForm().adminDetails browser,formData
	}

	def static  clickOnChangePrivilages = { browser, formData ->
		new ManageAdminsForm().clickOnChangePrivilages browser,formData
	}

	def static clickOnNewAdminChangePrivilage = { browser, formData ->
		new ManageAdminsForm().clickOnNewAdminChangePrivilage browser, formData
	}


	static final class ManageAdminsForm extends WebForm {

		//Manage Admins Page elements
		private static final def ADMIN_LIST = ".//div[@class='profile-info col-md-8']/h3/a"

		private static final def REMOVE_ADMINS = "//a[contains(@ng-click, 'removeSubAdminConfirmation')]"

		private static final def CHANGE_PRIVILEGES = "//a[@href='#/orgadmin/edituser']"

		private static final def CONFIRM_DELETE = "//span[contains(@class, 'ng-binding ng-scope') and text() = 'Yes']";

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"

		private static final def PAGINATION_FIRST = "//li[@class='pagination-first ng-scope']/a"

		private static final def PAGINATION_LAST = "//li[@class='pagination-last ng-scope']/a"

		private static final def EMAIL_ID = "//h5[@class='p_5 text-center ng-binding'][1]"

		private static final def CHANGE_PRIVILAGES= ".//div[@class='profile-info col-md-8']/a[2]"

		private static final def POP_YES ="html/body/div[3]/md-dialog/md-dialog-actions/button[2]"

		//Verify that added admin is displayed in the list
		def static final addedAdminDisplayed = { browser, formData ->

			def adminList = []
			def listOfAdmins = browser.getLists(ADMIN_LIST,"title")
			adminList.addAll(listOfAdmins);
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				def adminsInNextPage = browser.getLists(ADMIN_LIST,"title")
				adminList.addAll(adminsInNextPage);
			}
			if(adminList.containsAll(KPCommonPage.adminEmailId)){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "AdminDetailsIssue", "Created Admin's email does not match with expected email id ")
			}
		}

		//to remove an admon from the admins list
		def static final removeAnAdmin = { browser, formData ->

			def result = false ;
			def xpathToRemoveAdmin ;

			if(!browser.checkEnabled(PAGINATION_NEXT)){
				xpathToRemoveAdmin = ManageAdminsForm.removeAdminXpath(browser, formData)
			} else {
				while(browser.checkEnabled(PAGINATION_NEXT)){
					browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
					browser.click PAGINATION_NEXT
					browser.delay(1500)
					xpathToRemoveAdmin = ManageAdminsForm.removeAdminXpath(browser, formData)
				}
			}
			if(xpathToRemoveAdmin != null){
				browser.clickElement(xpathToRemoveAdmin)
				browser.delay(2000)
				if(CONFIRM_DELETE !=null){
					browser.click CONFIRM_DELETE
					browser.delay(2000)
					return new SuccessOutcome()
				} else {
					return KPCommonPage.returnFailureOutcome(browser, "ConfirmDeleteAdminIssue", "Removing an admin in not confirmed to remove")
				}
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "RemoveAdminIssue", "Admin has not been removed from admins list")
			}
		}

		//Verify that admin has been removed from the admins list
		def static final isAdminRemoved = { browser, formData ->
			def res = false ;
			if(browser.checkEnabled(PAGINATION_FIRST)){
				browser.click PAGINATION_FIRST
				browser.delay(1000)
				res = ManageAdminsForm.checkDisplayedAdmin(browser, formData)
				while(browser.checkEnabled(PAGINATION_NEXT)){
					browser.click PAGINATION_NEXT
					browser.delay(1000)
					res = ManageAdminsForm.checkDisplayedAdmin(browser, formData)
					if(res == true){
						return KPCommonPage.returnFailureOutcome(browser, "RemoveAdminIssue", "Admin has not been deleted from the admin's List")
					}
				}
			} else if(!browser.checkEnabled(PAGINATION_FIRST) && !browser.checkEnabled(PAGINATION_LAST)){
				res = ManageAdminsForm.checkDisplayedAdmin(browser, formData)
			}
			if(res){
				return KPCommonPage.returnFailureOutcome(browser, "RemoveAdminIssue", "Admin supposed to be removed is still displaying in the list")
			} else {
				return new SuccessOutcome()
			}
		}

		def static removeAdminXpath(def browser,def formData){

			def xpathToRemoveAdmin;
			def listOfAdmins = browser.getLists(ADMIN_LIST,"title")
			def removeAdminList =  browser.getListElements(REMOVE_ADMINS)
			for(int i=0;i<=listOfAdmins.size()-1;i++){
				if(listOfAdmins.get(i).trim().equalsIgnoreCase(KPCommonPage.adminEmailId[0])){
					browser.scrollToElement(removeAdminList[i])
					xpathToRemoveAdmin = removeAdminList[i];
					break;
				}
			}
			return xpathToRemoveAdmin;
		}

		def static checkDisplayedAdmin(def browser,def formData){

			def result = false ;
			def listOfAdmins = browser.getLists(ADMIN_LIST,"title")
			for(int i=0;i<=listOfAdmins.size()-1;i++){
				if(listOfAdmins.get(i).trim().equalsIgnoreCase(KPCommonPage.adminEmailId[0])){
					result = true;
					break;
				}
			}
			return result;
		}

		def static clickOnAdminName = { browser, formData ->
			def adminToBeClicked
			def namesOfAdmin= browser.getLists(ADMIN_LIST,"title")
			def pathOfAdmin= browser.getListElements(ADMIN_LIST)
			for(int i=0;i<=namesOfAdmin.size();i++){
				if(namesOfAdmin.get(i).equalsIgnoreCase(KPCommonPage.adminEmailId[0])){
					browser.scrollToElement(pathOfAdmin[i])
					adminToBeClicked = pathOfAdmin[i];
					break;
				}
			}
			if(adminToBeClicked != null){
				browser.clickElement(adminToBeClicked)
				browser.delay(1000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ClickAdminNameIssue", "Admin name cannot be clicked")
			}
		}
		def static adminDetails = { browser, formData ->
			def adminEmailOnProfile=browser.gettext(EMAIL_ID).trim()
			println "adminEmailOnProfile::" + adminEmailOnProfile
			if(KPCommonPage.adminEmailId[0].equalsIgnoreCase(adminEmailOnProfile)){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "EmailIdMatchIssue", "Admin email Id does not match")
			}
		}

		def static clickOnChangePrivilages= { browser, formData->
			def adminPrivilagesToBeChanged
			def adminEmail= browser.getLists(ADMIN_LIST ,"title")
			def changePrivilagePath= browser.getListElements(CHANGE_PRIVILAGES)
			for(int i=0;i<adminEmail.size();i++){
				if(adminEmail[i].equalsIgnoreCase(KPCommonPage.userName)){
					adminPrivilagesToBeChanged=changePrivilagePath[i]
					break
				}
			}
			if(adminPrivilagesToBeChanged != null){
				browser.clickElement(adminPrivilagesToBeChanged)
				browser.delay(4000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ChangePrivilagesLinkIssue", "Admin Change Privilages link cannot be clicked")
			}
		}

		def static clickOnNewAdminChangePrivilage ={ browser, formData->
			def adminPrivilagelinkClick
			def emailIdOfAdmins= browser.getLists(ADMIN_LIST,"title")
			def changePrivilagePath= browser.getListElements(CHANGE_PRIVILAGES)
			for(int i=0; i<emailIdOfAdmins.size();i++){
				if(emailIdOfAdmins[i].equalsIgnoreCase(KPCommonPage.adminEmailId[0])){
					adminPrivilagelinkClick=changePrivilagePath[i]
					break
				}
			}
			if(adminPrivilagelinkClick !=null){
				browser.scrollToElement(adminPrivilagelinkClick)
				browser.clickElement(adminPrivilagelinkClick)
				browser.delay(2000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ChangePrivilagesLinkIssue", "Admin Change Privilages link cannot be clicked")
			}
		}
	}
}