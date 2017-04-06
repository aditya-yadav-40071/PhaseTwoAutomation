package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage


final class ForgotPasswordPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new ForgotPasswordForm().populateFields(browser, formData);
	}

	//Override
	def submit = {browser, formKey, formData ->
		new ForgotPasswordForm().submit(browser, formData)
	}

	static final class ForgotPasswordForm extends WebForm {

		//Login form elements
		private static final def  USERID = ".//div[@id='passwordModal']/div[@class='modal-body']/div/div/input[@id='userName']"

		private static final def SUBMIT = ".//div[@class='login_btn']/button[@ng-click='validateUserName(resetPassword)']"

		private static final def FIELDS = [USERID];

		// the error fields.
		private static final def FORM_ERROR = "//span[@class='ng-binding ng-scope']"

		private static final def FIELD_ERROR = " .//*[@class='form-group login_form']/div/span/span"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		private static final def FIELD_REQUIRED_KEY = "field_req"

		private static final def FIELD_REQUIRED_MSG = "fill out"

		//error message map (Key-Value Pair)
		def static final forgotPasswordErrorMessageMap = [
			wrong_msg         : "Something went wrong",
			reset_success_msg : "password+reset+successfully",
			field_req         : "Please fill out this field."]

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
			println "Actual Validation Message is ::: "+actualValidationMsg
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, forgotPasswordErrorMessageMap)
			println "Actual Validation Message key is ::: "+actualValidationMsgKeys
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(5000)
			def actErrMsgs = []
			//To get the html5 tooltip error messages
			for(int i =0; i<= formFields.size()-1; i++){
				if( data[i].size() == 0 && browser.gettext(formFields[i], "required").equalsIgnoreCase("true")){
					if(!actErrMsgs.contains("Please fill out this field.")){
						actErrMsgs.add("Please fill out this field.")
					}
				}
			}
			//Get the error messages
			def errMsgs = browser.getValidationMessages errFields // get the validation messages from the current page.
			//add both error messages
			for(int j =0; j<= errMsgs.size() - 1; j++){
				actErrMsgs.add(errMsgs[j])
			}
			actErrMsgs
		}
	}
}