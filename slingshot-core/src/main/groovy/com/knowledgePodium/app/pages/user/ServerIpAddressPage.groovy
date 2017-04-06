package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage


final class ServerIpAddressPage extends WebPage {

	def populateData = { browser, formKey, formData ->
		new ServerIpAddressPageForm().populateFields(browser, formData);
	}

	def submit = { browser, formKey, formData ->
		new ServerIpAddressPageForm().submit(browser, formData)
	}

	static final class ServerIpAddressPageForm extends WebForm {

		//Login form elements
		private static final def  IPADD = ".//*[@id='ipModall']/div/div/form/div[1]/div/input"

		private static final def FIELDS = [IPADD];

		// the error fields.
		private static final def FORM_ERROR = "//span[@class='ng-binding ng-scope']"

		private static final def FIELD_ERROR = ".//*[@id='ipModall']/div/div/form/div[1]/div/span/span"

		private static final def SUBMIT = ".//*[@id='ipModall']/div/div/form/div[2]/div/button"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final ipAddressPageErrorMessageMap = [
			ip_req     : "IP is required",
			ip_success : "Server IP saved successfully",
			invalid_ip : "Server IP is not reachable.Please enter valid server IP or contact admin"]

		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				outcome = WebForm.enterData(browser, formData, FIELDS, SUBMIT, WAIT_REQ_FIELDS)
			}
			return outcome;
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, SUBMIT, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, ipAddressPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(4000)
			browser.getValidationMessages errFields // To get the validation messages from the current page.
		}
	}
}
