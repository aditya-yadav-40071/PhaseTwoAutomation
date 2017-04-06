package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage


final class AddAdminPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		println ("Populate data method in Add Admin Page")
		new AddAdminForm().populateFields(browser, formData);
	}

	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in Add Admin Page")
		new AddAdminForm().submit(browser, formData)
	}
	
	def static addAnotherAdminErrorMessage = { browser, formData ->
		println ("Add Another Admin Error Message method in Add Admin Page")
		new AddAdminForm().addAnotherAdminErrorMessage(browser, formData)
	}

	static final class AddAdminForm extends WebForm {

		//Login form elements
		private static final def  FIRST_NAME = ".//*[@name='fname']"

		private static final def  MIDDLE_NAME = ".//*[@name='mname']"

		private static final def  LAST_NAME = ".//*[@name='lname']"

		private static final def  EMAIL = ".//*[@id='subAdminMailId']"

		private static final def  SEND_INVITATION = ".//md-checkbox[@ng-model='subadminform.sendinvitationstatus']"

		private static final def  PASSWORD = ".//*[@id='subAdminPassword']"

		private static final def  CONFIRM_PASSWORD = ".//*[@id='confirmPassword']"

		private static final def  MANAGE_TRAINER = ".//md-checkbox[@ng-model='subadminform.changePrivilegesForTrainer']"

		private static final def  MANAGE_JOB = ".//md-checkbox[@ng-model='subadminform.changePrivilegesForHr']"

		private static final def SAVE = ".//button[@ng-click='submitRegistrationDetails(subadminform)']"
		
		private static final def ADMINERRMSG = ".//div[@ng-transclude='']/span"

		private static final def FIELDS = [FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL, SEND_INVITATION, PASSWORD, CONFIRM_PASSWORD, MANAGE_TRAINER, MANAGE_JOB]

		// the error fields.
		private static final def FORM_ERROR = ".//span[@class='error_message']"

		private static final def FORM_ERROR2 = ".//span[@class='ng-binding ng-scope']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR,FORM_ERROR2]

		//error message map (Key-Value Pair)
		def static final addAdminPageErrorMessageMap = [ fname_req :'First Name is required.',
			email_req :'Email ID is required.',
			invalid_email :'Enter a valid email ID',
			email_already_exist :'EmailId already exists',
			pass_req :'Password is required',
			confirm_pass_req :'Re-enter Password is required',
			pass_combination_error :'Password should be a combination of minimum 8 characters containing a capital letter,special character and a digit',
			pass_missmatch :'Password does not match',
			enter_corr_pass :'Enter correct password',
			success_message : 'Sub-admin/Trainer/HR registered successfully.'
		]

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("AddAdminForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			println "outcome::"+ outcome
			if(outcome.isSuccess()){
				browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_NAME))
				for(int i=0;i<=FIELDS.size()-1;i++){
					if(FIELDS[i].equals(EMAIL)){
						if(formData[i].contains("@")){
							def emailToEnter
							if(formData[i].equalsIgnoreCase(KPCommonPage.userName)){
								emailToEnter = KPCommonPage.userName
							} else {
								emailToEnter = KPCommonPage.generateRandomEmailAddress(formData[i])
								println "emailToEnter::::::::::::::::::::::"+emailToEnter
								KPCommonPage.adminEmailId.add(emailToEnter)
							}
							browser.populateField(FIELDS[i], emailToEnter)
						} else {
							browser.populateField(FIELDS[i], formData[i])
						}
					} else {
						if(FIELDS[i].equals(MANAGE_TRAINER)){
							browser.scrollToElement(browser.getElement(Browser.XPATH, MANAGE_TRAINER))
						}
						if(FIELDS[i].equals(PASSWORD)){
							def password= KPCommonPage.adminPassword = formData[i]
						}
						def tagName = browser.getTagName(FIELDS[i])
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
					}
				}
				outcome = new SuccessOutcome()
			}
			return outcome
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, SAVE, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, addAdminPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(3000)
			} else {
				browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_NAME))
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
		
		def static final addAnotherAdminErrorMessage = { browser, formData->
			def mandatoryErrMsg = browser.gettext(FORM_ERROR2)
			if(mandatoryErrMsg.equalsIgnoreCase(formData[0])){
				return new SuccessOutcome()
			}else {
			return KPCommonPage.returnFailureOutcome(browser, "ErrorMessageIssue", "The error message displayed does not match")
			}
		}
	}
}
