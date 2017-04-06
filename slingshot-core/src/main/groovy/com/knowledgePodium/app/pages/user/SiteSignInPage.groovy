package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage


final class SiteSignInPage extends WebPage {

	def populateData = { browser, formKey, formData ->
		new SiteSignInPageForm().populateFields(browser, formData);
	}

	def submit = { browser, formKey, formData ->
		new SiteSignInPageForm().submit(browser, formData)
	}

	def static consumerName = { browser, formData ->
		new SiteSignInPageForm().consumerName(browser, formData)
	}

	static final class SiteSignInPageForm extends WebForm {

		//Login form elements
		private static final def  USERID = "html/body/div[1]/div[1]/div[1]/div[3]/div/div/form/div/div[1]/div/input"

		private static final def PASSWORD = ".//input[@name='password']"

		private static final def SIGNIN = ".//*[@id='loginModal']/div/div/form/div/div[3]/button"

		private static final def USERNAME = ".//*[@id='primary_menu']/div/span"

		private static final def FIELDS = [USERID, PASSWORD];

		// the error fields.
		private static final def FORM_ERROR = "//span[@class='ng-binding ng-scope']"

		private static final def FIELD_ERROR = ".//*[@class='form-group login_form']/div/span/span"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final signInPageErrorMessageMap = [
			uname_req : "Username is required",
			pword_req : "Password is required.",
			invalid_credentials : "Invalid Credentials",
			wrong_msg : "Something went wrong.Try Again"]

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("SiteSignInPageForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				outcome = WebForm.enterData(browser, formData, FIELDS, SIGNIN, WAIT_REQ_FIELDS)
			}
			return outcome;
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, SIGNIN, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, signInPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(500)
			browser.getValidationMessages errFields // To get the validation messages from the current page.
		}

		def static consumerName = { browser, data ->
			def userName = browser.gettext(USERNAME)
			for(int i=0; i<data.size();i++){
				if(userName.equalsIgnoreCase(data[0])){
					return new SuccessOutcome()
				}else {
					return KPCommonPage.returnFailureOutcome(browser, "ConsumerNameIssue", "Consumer name does not match")
				}
			}
		}
	}
}
