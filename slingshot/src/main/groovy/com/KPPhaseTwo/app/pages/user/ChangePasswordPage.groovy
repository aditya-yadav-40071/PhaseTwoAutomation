package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Samir on 21/02/2017
 */

final class ChangePasswordPage extends WebPage {

	//Override	
	def populateData = {browser, formKey, formData ->
		new ChangePasswordForm().populateFields(browser, formData);
	}	
	
	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in ChangePasswordPage")
		new ChangePasswordForm().submit(browser, formData)
	}	
	
	static final class ChangePasswordForm extends WebForm {

		//ChangePassword form elements
		private static final def OLD_PASSWORD = ".//*[@id='oldPassword']" 

		private static final def NEW_PASSWORD = ".//*[@id='newPassword']"  		

		private static final def CONFIRM_PASSWORD = ".//*[@id='confirmPassword']"
		
		private static final def SUBMIT = "//input[@value='SUBMIT']"
		
		//private static final def PROFILE_IMAGE = ""
		
		private static final def FIELDS = [OLD_PASSWORD, NEW_PASSWORD, CONFIRM_PASSWORD]	
		
		// the error fields.
		private static final def FORM_ERROR = "//div[@class='error_message']"
		
		private static final def FIELD_ERROR = "//span[@class='error_message']"
		
		private static final def SUCCESS_MSG = ".//span[@class='ng-binding ng-scope']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR, SUCCESS_MSG]
		
		//error message map (Key-Value Pair)
		def static final changePasswordPageErrorMessageMap = [ old_pass_req:"Old Password is required",
			old_pass_invalid:"Not a valid Old Password",
			new_pass_req:"New Password is required",
			pass_combination_err:"Password should be a combination of minimum 8 characters, containing a capital letter,special character and a digit",
			same_new_pass:"New Password cannot be same as Old Password",
			retype_pass:"Retype your Password",
			pass_not_match:"Password does not match",
			new_pass_invalid:"Enter correct new password",
			success_change_pwd:"Password changed successfully"
		]

		//To enter data
		def static final populateFields = { browser, formData ->			
			println ("ChangePasswordForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				outcome = WebForm.enterData(browser, formData, FIELDS, SUBMIT, WAIT_REQ_FIELDS)
				
			} 
			return outcome
		}
		
		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {			
			def actualValidationMsg = submitForm browser, FIELDS, SUBMIT, data, ERROR_MESSAGE_FIELDS	
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, changePasswordPageErrorMessageMap)			
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}
		
		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(2000)
			browser.getValidationMessages errFields // get the validation messages from the current page.			
		}	
	}	
}
