package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


final class IndividualRegisterPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new IndividualRegisterForm().populateFields(browser, formData);
	}

	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in IndividualRegisterPage")
		new IndividualRegisterForm().submit(browser, formData)
	}

	static final class IndividualRegisterForm extends WebForm {

		//Login form elements
		private static final def  FIRST_NAME = ".//*[@name='firstname']"

		private static final def MIDDLE_NAME = ".//*[@name='mname']"

		private static final def LAST_NAME = ".//*[@name='lname']"

		private static final def EMAIL_ID = ".//*[@id='emailId']"

		private static final def PASSWORD = ".//*[@id='password']"

		private static final def CONFIRM_PASSWORD = ".//*[@id='confirmPassword']"

		private static final def CITY = ".//*[@id='city']"

		private static final def STATE = ".//*[@id='state']"

		private static final def COUNTRY = ".//*[@id='country']"

		private static final def PIN = ".//*[@id='pin']"

		private static final def MOBILE_NO = ".//*[@id='mobileno']"

		private static final def OTP = ".//*[@id='otp']"

		private static final def AGREE_TO_TERMS = ".//label[@for='remember_me']"

		private static final def REGISTER = ".//button[@ng-click='submitRegistrationDetails(register)']"

		private static final def AUTO_CITY_LIST = ".//div[@class='pac-container pac-logo hdpi'][last()]/descendant::span[@class='pac-matched']"

		private static final def FIELDS = [FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL_ID, PASSWORD, CONFIRM_PASSWORD, CITY, PIN, MOBILE_NO, OTP, AGREE_TO_TERMS]

		// the error fields.
		private static final def FORM_ERROR = ".//span[@class='error_message']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR]

		//error message map (Key-Value Pair)
		def static final individualRegPageErrorMessageMap = [fname_req : 'First Name is required.',
			emailId_req :'Email ID is required.',
			invalid_email :'Enter a valid Email ID',
			email_already_exist : 'Email ID already exists',
			pass_req :'Password is required.',
			pass_missmatch : 'Password does not match',
			pass_combination_err : 'Password should be a combination of minimum 8 characters, containing a capital letter,special character and a digit',
			enter_corr_pass : 'Enter correct password',
			city_req :'City is required.',
			pin_req :'Pin code is required.',
			pincode_len : 'Pin code should be of 6 digits',
			mob_req :'Mobile Number is required',
			invalid_mob_no : 'Enter a valid Mobile Number',
			otp_req : 'OTP is required.',
			otp_len : 'OTP should be of 6 digits'
		]

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("IndividualRegisterForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<=FIELDS.size()-1;i++){
					browser.scrollToElement(browser.getElement(Browser.XPATH, FIELDS[i]))
					if(FIELDS[i].equals(EMAIL_ID)){
						if(formData[i].contains("@")){
							def emailToEnter = KPCommonPage.generateRandomEmailAddress(formData[i])
							browser.populateField(FIELDS[i], emailToEnter)
						} else {
							browser.populateField(FIELDS[i], formData[i])
						}
					} else if(FIELDS[i].equals(MOBILE_NO) && formData[i] != ""){
						if(formData[i].size()==10){
							long mobNoToEnter = KPCommonPage.generateRandomCellNo()
							browser.populateField(FIELDS[i], String.valueOf(mobNoToEnter))
						} else {
							browser.populateField(FIELDS[i], formData[i])
						}
					} else if(FIELDS[i].equals(CITY) ){
						browser.scrollToElement(browser.getElement(Browser.XPATH, FIELDS[i]))
						browser.delay(2000)
						if(formData[i] != ""){
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(3000)
							KPCommonPage.selectAutoComplete(browser, AUTO_CITY_LIST, formData[i].trim())
						} else{
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(2000)
						}
					} else {
						def tagName = browser.getTagName(FIELDS[i])
						WebForm.inputData(browser, FIELDS[i], tagName,  formData[i])
						browser.delay(1000)
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
			def actualValidationMsg = submitForm browser, FIELDS, REGISTER, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, individualRegPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(3000)
			} else {
				browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_NAME))
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
	}
}
