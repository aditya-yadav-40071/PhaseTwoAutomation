package com.KPPhaseTwo.app.dsl

import com.KPPhaseTwo.app.pages.user.LoginPage
import com.KPPhaseTwo.app.pages.admins.ManageAdminsPage
import com.KPPhaseTwo.app.pages.user.DashboardPage
import com.KPPhaseTwo.app.pages.admins.AddAdminPage
import com.KPPhaseTwo.app.pages.admins.ChangePrivilagesPage
/**
 * Created by Sandhya on 27/9/2015
 */
class UserManager {

	//To verify that the logged in user is displayed in the edit profile page
	def correctUserLoggedIn = { browser, formData ->
		LoginPage.correctUserLoggedIn browser, formData
	}

	//Verify that added admin is displayed in the list
	def addedAdminDisplayed = { browser, formData ->
		ManageAdminsPage.addedAdminDisplayed browser,formData
	}

	//to remove an admon from the admins list
	def removeAnAdmin = { browser, formData ->
		ManageAdminsPage.removeAnAdmin browser,formData
	}

	//Verify that admin has been removed from the admins list
	def isAdminRemoved = { browser, formData ->
		ManageAdminsPage.isAdminRemoved browser,formData
	}

	//To logout from the application
	def logOut = { browser, formData ->
		DashboardPage.logOut browser,formData
	}

	//Login to the application with removed admin's credential
	def loginAsAnAdmin = { browser, formData ->
		LoginPage.loginAsAnAdmin browser,formData
	}

	def clickOnAdminName = { browser, formData ->
		ManageAdminsPage.clickOnAdminName browser,formData
	}

	def adminDetails = { browser, formData ->
		ManageAdminsPage.adminDetails browser,formData
	}

	def addAnotherAdminErrorMessage = { browser, formData ->
		AddAdminPage.addAnotherAdminErrorMessage browser, formData
	}

	def loggedInAsTrainer = { browser, formData ->
		DashboardPage.loggedInAsTrainer browser, formData
	}

	def loggedInAsJobAdmin = { browser, formData ->
		DashboardPage.loggedInAsJobAdmin browser, formData
	}

	def loggedInAsSubAdmin = { browser, formData ->
		DashboardPage.loggedInAsSubAdmin browser, formData
	}
	
	def clickOnChangePrivilages = { browser, formData ->
		ManageAdminsPage.clickOnChangePrivilages browser, formData
	}
	
	def clickOnNewAdminChangePrivilage = { browser, formData ->
		ManageAdminsPage.clickOnNewAdminChangePrivilage browser, formData
	}
	
	def adminPrivilageChanged = { browser, formData ->
		ChangePrivilagePage.adminPrivilageChanged browser, formData
	}
}
