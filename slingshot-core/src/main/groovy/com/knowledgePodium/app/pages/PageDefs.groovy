package com.knowledgePodium.app.pages

import groovy.transform.ToString;
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage

class PageDefs {

	private static List<PageDefEntry> pageDefEntries = new ArrayList();
	static {
		// Define the Pages available...  (page-key, page-name,  page-className)
		pageDefEntries.add(new PageDefEntry("home",  "Learner Home",null));
		pageDefEntries.add(new PageDefEntry("signIn", "Sign In", "com.knowledgePodium.app.pages.user.SiteSignInPage"))
		pageDefEntries.add(new PageDefEntry("dashboard", "Dashboard", "com.knowledgePodium.app.pages.user.DashboardPage" ))
		pageDefEntries.add(new PageDefEntry("serverIpAddress", "Server IP Address", "com.knowledgePodium.app.pages.user.ServerIpAddressPage"))
		pageDefEntries.add(new PageDefEntry("courseDetail", "Course Detail", "com.knowledgePodium.app.pages.user.CourseDetailPage"))
		pageDefEntries.add(new PageDefEntry("forgotPass", "Forgot Password", "com.knowledgePodium.app.pages.user.ForgotPasswordPage"))
		pageDefEntries.add(new PageDefEntry("changePassword", "Change Password", "com.knowledgePodium.app.pages.user.ChangePasswordPage"))
		pageDefEntries.add(new PageDefEntry("startClass", "Start Class", "com.knowledgePodium.app.pages.user.ClassRoomPage"))
	}
	//Get Key for the current page
	public static getCurrentPageKey(def browser){
		def pageName;
		pageName = browser.getTitle()
		if(pageName.equals("Learner Home")&& browser.gettext(".//*[@id='myModalLabel']")){
			pageName=browser.gettext(".//h4[contains(text(),'Sign In')]");
			browser.delay(1000)
		}else if(pageName.equals("Learner Home")&& browser.gettext(".//h4[contains(text(),'Forgot Password')]")){
			pageName=browser.gettext(".//h4[contains(text(),'Forgot Password')]");
		}
		else if(pageName.equals("Learner Home")&& browser.gettext(".//*[@id='ipModall']/div/div/div/h4")){
			pageName=browser.gettext(".//*[@id='ipModall']/div/div/div/h4");
		}
		println "Page Name :" +pageName;
		return findKeyByPageName(pageName.trim());
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
