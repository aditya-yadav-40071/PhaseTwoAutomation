package com.knowledgePodium.app.dsl

import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage


/* Class to take care of operations related to all click actions */
class ClickAction {

	private static Map<String, String> xPathMapping;

	private static final def LINK = "linkname"

	static {

		xPathMapping = new HashMap<String, String>();

		// List of buttons/links with its XPath in the path

		xPathMapping.put("signIn", ".//*[@id='primary_menu']/ul/li/a");

		xPathMapping.put("profileImageIcon", ".//*[@id='primary_menu']/div/a/img");

		xPathMapping.put("logout", ".//ul[@class='dropdown-menu pull-right']/li[2]/a");

		xPathMapping.put("closeButton", ".//div[@class='modal-header login_header']/button");

		xPathMapping.put("startContinue", ".//button[@class='btn btn-lg start_learn_btn kp-btn']")

		xPathMapping.put("exit", ".//div[@class='col-md-3 col-xs-3']/div/button")

		xPathMapping.put("forgotPass", ".//a[@class='forgot_password']")

		xPathMapping.put("changePassword", ".//*[@id='primary_menu']/div/ul/li[1]/a")

		xPathMapping.put("closeIpbtn", ".//*[@id='ipModall']/div/div/div/button")

		xPathMapping.put("nextbtn", ".//div[@class='col-md-6']/div/button[@class='btn kp-btn']")

		xPathMapping.put("backbtn", ".//div[@class='col-md-6']/div/button[@ng-show='isShowPrev']")
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
			browser.click xpath
			waitAfterClick(browser, xpath)
			return new SuccessOutcome();
		}
	}


	//Delay before click
	def static waitBeforeClick(def browser, def xpath){
		if(xpath.equals("html/body/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div/div[1]/div[2]/h2[@role='button']")){
			browser.delay(2000)
		}
		if(xpath.equals(".//div[@class='col-md-6']/div/button[@class='btn kp-btn']")){
			browser.delay(5000)
			browser.scrollToElement(".//div[@class='col-md-6']/div/button[@class='btn kp-btn']")
		}
		browser.delay(2000)

		if(xpath.equals(".//*[@id='primary_menu']/ul/li/a")){
			browser.delay(2000)
		}
	}

	//Delay after click
	def static waitAfterClick(def browser, def xpath){
		browser.delay(2000)
		if(xpath.equals(".//button[@class='btn btn-lg start_learn_btn kp-btn']")){
			browser.delay(35000)
		}
		if(xpath.equals(".//div[@class='col-md-6']/div/button[@class='btn kp-btn']")||xpath.equals(".//div[@class='col-md-6']/div/button[@ng-show='isShowPrev']")){
			browser.delay(6000)
		}
		if(xpath.equals(".//*[@id='primary_menu']/ul/li/a")){
			browser.delay(2000)
		}
		if(xpath.equals(".//div[@class='col-md-3 col-xs-3']/div/button")){
			browser.delay(2000)
		}
	}
}
