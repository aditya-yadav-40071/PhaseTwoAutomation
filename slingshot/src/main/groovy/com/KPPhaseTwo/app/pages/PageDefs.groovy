package com.KPPhaseTwo.app.pages

import groovy.transform.ToString;

import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

class PageDefs {
	
	private static List<PageDefEntry> pageDefEntries = new ArrayList();
	static {
		// Define the Pages available...  (page-key, page-name, page-className)		
		//User	
		pageDefEntries.add( new PageDefEntry("home", "KP Consumer Home", null))	
		pageDefEntries.add( new PageDefEntry("login", "Login User", "com.KPPhaseTwo.app.pages.user.LoginPage"))
		pageDefEntries.add( new PageDefEntry("bifurcateUser", "Bifurcate User", null))
		pageDefEntries.add( new PageDefEntry("companyRegistration", "Company Registration", "com.KPPhaseTwo.app.pages.user.CompanyRegistrationPage"))
		pageDefEntries.add( new PageDefEntry("dashboard", "Dashboard", "com.KPPhaseTwo.app.pages.user.DashboardPage"))
//		pageDefEntries.add( new PageDefEntry("editProfile", "Edit Profile", null))
		pageDefEntries.add( new PageDefEntry("individaulRegister", "Register User", "com.KPPhaseTwo.app.pages.user.IndividualRegisterPage"))
		
		//User Profile
		pageDefEntries.add( new PageDefEntry("adminViewProfile", "View Public Profile", "com.KPPhaseTwo.app.pages.admins.ManageAdminsPage"))
		
		
		//Forgot password
		pageDefEntries.add( new PageDefEntry("forgotPassword", "Forgot Password", "com.KPPhaseTwo.app.pages.user.ForgotPasswordPage"))
		
		//manage Admins
		pageDefEntries.add( new PageDefEntry("manageAdmins", "Manage Admins", "com.KPPhaseTwo.app.pages.admins.ManageAdminsPage"))
		pageDefEntries.add( new PageDefEntry("addAdmin", "Add Admin", "com.KPPhaseTwo.app.pages.admins.AddAdminPage"))
		
		//Change password
		pageDefEntries.add( new PageDefEntry("changePassword", "Change Password", "com.KPPhaseTwo.app.pages.user.ChangePasswordPage"))
		
		//Search Pods
		pageDefEntries.add( new PageDefEntry("searchPods", "Search Pods", "com.KPPhaseTwo.app.pages.pods.SearchPodPage"))
		pageDefEntries.add( new PageDefEntry("podDetails", "Pod Details", null))
		pageDefEntries.add( new PageDefEntry("podWishList", "Pods Wishlist", null))
		
		//terms and privacy
		pageDefEntries.add( new PageDefEntry("privacyPolicy", "Privacy Policy", null))
		pageDefEntries.add( new PageDefEntry("termsOfUse", "Terms of Use", null))
		
		//Change Privlages page
		pageDefEntries.add( new PageDefEntry("changePrivilages", "Change privileges", "com.KPPhaseTwo.app.pages.admins.ChangePrivilagesPage"))
		
		//Post Job
		pageDefEntries.add( new PageDefEntry("postJob","Job Post",null))
		pageDefEntries.add( new PageDefEntry("jobPostList","Job Postings List","com.KPPhaseTwo.app.pages.admins.PostJobPage"))
		pageDefEntries.add( new PageDefEntry("editProfile","Edit Profile","com.KPPhaseTwo.app.pages.user.UserEditProfilePage"))
	}
	
	//Get Key for the current page
	public static getCurrentPageKey(def browser){
		def pageName
		
		pageName = browser.getTitle()		
		 		 
		println "Page Name :" +pageName
		return findKeyByPageName(pageName.trim())
	}	
	
	
	/* Get PageDefEntry object for specified key */
	public static PageDefEntry getPageDefEntry (def pageKey) {
		for(PageDefEntry pageDefEntry : pageDefEntries) {
			if(pageDefEntry.key.equalsIgnoreCase(pageKey))
				return pageDefEntry;
		}
		return null;
	}
	
	private static findKeyByPageName(String pageName){		
		for(PageDefEntry pageDefEntry : pageDefEntries) {
			if(pageDefEntry.name.equalsIgnoreCase(pageName))
				return pageDefEntry.key;
		}
		return null;		
	}
}
