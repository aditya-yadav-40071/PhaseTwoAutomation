package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage


final class ChangePasswordPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new ChangePasswordPageForm().populateFields(browser, formData);
	}

	//Override
	def submit = {browser, formKey, formData ->
		new ChangePasswordPageForm().submit(browser, formData)
	}

	static final class ChangePasswordPageForm extends WebForm {

		//Login form elements
		private static final def  OLD_PASSWORD = ".//*[@id='oldPassword']"

		private static final def NEW_PASSWORD = ".//*[@id='newPassword']"

		private static final def CONFIRM_PASSWORD= ".//*[@id='confirmPassword']"

		private static final def SUBMIT = ".//button[@type='submit']" 

		private static final def FIELDS = [OLD_PASSWORD, NEW_PASSWORD, CONFIRM_PASSWORD];

		// the error fields.
		private static final def FORM_ERROR = "html/body/div[1]/div[1]/div/span"

		private static final def FIELD_ERROR = ".//div[@class='error_message']"


		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final changePassPageErrorMessageMap = [
			passChange_Success : "Password changed successfully",
			oldPass_req        : "Old Password is required.",
			newPass_req        : "Password is required.",
			confirmPass_retype : "Retype your Password",
			unMatch_Pass       : "Password doesn't match",
			invalid_oldPass    : "Invalid old password.",
			wrong_msg          : "Something went wrong.Try Again"]

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("ChangePasswordPageForm.populateFields - data: " + formData)
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
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, changePassPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(200)
			browser.getValidationMessages errFields // To get the validation messages from the current page.
		}
	}
}
