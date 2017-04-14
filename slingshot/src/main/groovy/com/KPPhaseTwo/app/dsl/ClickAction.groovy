package com.KPPhaseTwo.app.dsl

import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage


/* Class to take care of operations related to all click actions */
class ClickAction {
	
	private static Map<String, String> xPathMapping;
	
	private static final def LINK = "linkname"
	
	static {
		
		xPathMapping = new HashMap<String, String>();
		
		//List of buttons/links with its XPath in the path	
		xPathMapping.put("login", "//button[contains(text(),'LOGIN')]");
		xPathMapping.put("leftMenuButton", "//button[@aria-expanded='false'][@role='button']");
		xPathMapping.put("registerFrmLftMenu", "//a[contains(text(), 'Register')]");
		xPathMapping.put("registerFrmPage", "//button[contains(text(), 'REGISTER')]");
		xPathMapping.put("oganizationLnk", "//h4[contains(text(), 'ORGANIZATION')]");
		xPathMapping.put("nextBtn", "//button[contains(text(),'NEXT')]");
		xPathMapping.put("loginFromLeftMenu", ".//a[@href='#/account/login']");
		xPathMapping.put("individualRegister", ".//*[@class='kp-individual kp-large-two-tone-icons']/span[@class='path3']")
		xPathMapping.put("resgisterFromHome", ".//button[contains(@class, 'margin-auto display-block btn btn-primary btn-xlarge get-started-btn') and text() = 'REGISTER']")
		xPathMapping.put("loginFromHome", ".//button[contains(@class, 'margin-auto display-block btn btn-primary btn-xlarge get-started-btn') and text() = 'LOGIN']")
		xPathMapping.put("breadcrumb", ".//*[@id='breadcrumbox']/a[2]");
		xPathMapping.put("editProfile", "//a/*[contains(text(), 'Edit Profile')]");
		xPathMapping.put("dashbreadcrumb", ".//*[@id='breadcrumbox']/a[2]");
		//Change Password
		xPathMapping.put("changePassword", "//a[@href='#/clouduser/changepwd']");
		
		//Search Pods
		xPathMapping.put("searchPods", ".//li[@class='menu-name-default mb30']/a[@href='#/pods/search']");
		xPathMapping.put("searchPodButton", "//button[@class='full-width button-primary']");
		xPathMapping.put("firstPod", "//div[2]/div[1]/div/div/h3/a");
		xPathMapping.put("podWishlist", ".//a[@href='#/pods/wishlist'][contains(text(), 'Pod Wishlist')]");
		xPathMapping.put("dashBoardLink", ".//*[@id='breadcrumbox']/a[2]");
		//xPathMapping.put("viewAllWishList", ".//*[@id='breadcrumbox']/a[2]");
		
		//Forgot Password
		xPathMapping.put("forgotPassword", ".//*[@id='forgot_password']")
		xPathMapping.put("privacyPolicy", ".//a[@href='#/privacypolicy']");
		xPathMapping.put("termsOfUse", ".//a[@href='#/termsofuse']");
		xPathMapping.put("aboutUs", ".//a[@href='#/aboutus']");
		xPathMapping.put("contactUs", ".//a[@href='#/contactus']");
		xPathMapping.put("goToLoginPage", ".//*[@href='#/account/login']");
		xPathMapping.put("brandImageLink", ".//img[@class='brand_bLogin']");
		
		//Manage Admin
		xPathMapping.put("manageAdmins", ".//li[@ng-show='getAdminListUser']/a[@href='#/orgadmin/listuser']"); //.//ul[@class='dropdown-menu']/li[12]/a
		xPathMapping.put("manageAdminBreadCrumb", ".//*[@id='breadcrumbox']/a[@href='#/orgadmin/listuser']");
		xPathMapping.put("addAdmin", "//input[contains(@ng-click,'/orgadmin/adduser')]");
		xPathMapping.put("lastOfItemsPerPage", ".//div[@class='items-per-page-section pull-right pos-r']/label[last()]");
		xPathMapping.put("addAnotherAdmin", ".//button[contains(@ng-click,'showConfirm')]");
		xPathMapping.put("saveToAddAnotherAdmin", ".//button[@ng-click='dialog.hide()']/span");
		
		//Post Job
		xPathMapping.put("postJob", ".//a[@href='#/job/postJob']")
		xPathMapping.put("viewAllPostings", ".//a[@href='#/jobs/postlist']");
		xPathMapping.put("postJobBreadcrumb",".//*[@id='breadcrumbox']/a[4]");
		
		//User Edit Profile
		xPathMapping.put("userEditProfile",".//a[@class='profile-edit-link display-inline-block']/h5");
		xPathMapping.put("basicInfoArrowDown","//*[contains(text(), 'Basic Information*')]");
		xPathMapping.put("basicInfoArrowUp","//*[contains(text(), 'Basic Information*')]");
	}
	
	// Main function to take care of click actions
	def static performClick (def browser, def clickKey) {
		
		println ("Performing Click for key: " + clickKey);
		if(clickKey ==null) return;
		
		def xpath = xPathMapping.get(clickKey);
		println ("xpath for click is " + xpath);
		
		if(browser.getElement(Browser.XPATH, xpath) == null || !browser.checkEnabled(xpath)){
			def message = clickKey + "  not found/NotEnabled"
			println "Message   :" +message
			def fileName = clickKey+"Issue"
			return KPCommonPage.returnFailureOutcome(browser, fileName, message)
		}else{
		    waitBeforeClick(browser, xpath) 		
			browser.scrollToElement(browser.getElement(Browser.XPATH, xpath))
			browser.click xpath							
			waitAfterClick(browser, xpath)
			return new SuccessOutcome();
		}
	}	
	
		
	//Delay before click
	def static waitBeforeClick(def browser, def xpath){	
		/*if(xpath.equals("//button[contains(text(), 'REGISTER')]")){
			browser.delay(2000)
			browser.scrollToElement2("//button[contains(text(), 'REGISTER')]")
		}*/
		/*if(xpath.equals("//button[contains(text(),'LOGIN')]")){
			browser.delay(2000)
			browser.scrollToElement2("//button[contains(text(),'LOGIN')]")
		}*/	
		if(xpath.equals("//div[2]/div[1]/div/div/h3/a")){
			browser.delay(1000)
			KPCommonPage.podName = browser.gettext("//div[2]/div[1]/div/div/h3/a")
		}
		if(xpath.equals(".//li[@ng-show='getAdminListUser']/a[@href='#/orgadmin/listuser']")){
			KPCommonPage.adminEmailId.clear()
		}
		browser.delay(2000)
	}
	
	//Delay after click
	def static waitAfterClick(def browser, def xpath){
		if(xpath.equals("//button[@class='full-width button-primary']")){
			browser.delay(2000)
		}		
		browser.delay(3000)
	}	
	
}
