package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Samir on 17/02/2017
 */

final class CompanyRegistrationPage extends WebPage {

	//Override	
	def populateData = {browser, formKey, formData ->
		new CompanyRegistrationForm().populateFields(browser, formData);
	}	
	
	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in CompanyRegistrationPage")
		new CompanyRegistrationForm().submit(browser, formData)
	}	
	
	static final class CompanyRegistrationForm extends WebForm {

		//CompanyRegistration form elements
		private static final def ORGANIZATION_NAME = ".//*[@id='companyName']" 

		private static final def FIRST_NAME = ".//*[@id='fname']"  		

		private static final def MIDDLE_NAME = ".//*[@id='mname']"
		
		private static final def LAST_NAME = ".//*[@id='lname']"
		
		private static final def EMAIL_ID = ".//*[@id='emailContact']"
		
		private static final def INDUSTRY = "//input[@ng-change='onChange()'][@name='name']"
		
		private static final def CREATE_PASSWORD = ".//*[@id='password']"
		
		private static final def REENTER_PASSWORD = ".//*[@id='confirmPassword']"
		
		private static final def ADDRESS_LINE1 = ".//*[@id='address1']"
		
		private static final def ADDRESS_LINE2 = ".//*[@id='address2']"
		
		private static final def ADDRESS_LINE3 = ".//*[@id='address3']"
		
		private static final def CITY = ".//*[@id='city']"
		
		private static final def OTP = ".//*[@id='OTP']"
		
		//private static final def COUNTRY = ""
		
		private static final def PIN_CODE = "//input[@name='pincode']"
		
		private static final def MOBILE_NUM = ".//*[@id='mobileno']"
		
		private static final def REGISTER = "//button[@class='button-primary'][contains(text(), 'REGISTER')]"
		
		private static final def CITY_AUTOSELCT = ".//div[@class='pac-container pac-logo hdpi'][last()]/descendant::span[@class='pac-matched']"
		
		private static final def INDUSTRY_AUTOSELCT = "//div/ul/li[@ng-click='onSuggestedItemsClick(suggestion)']"
		
		private static final def FIELDS = [ORGANIZATION_NAME, FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL_ID, INDUSTRY, CREATE_PASSWORD, REENTER_PASSWORD, ADDRESS_LINE1, ADDRESS_LINE2, ADDRESS_LINE3, CITY, PIN_CODE, MOBILE_NUM, OTP]	
		
		// the error fields.
		private static final def FORM_ERROR = ""
		
		private static final def FIELD_ERROR = "//span[@class='error_message']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]
		
		//error message map (Key-Value Pair)
		def static final CompanyRegistrationPageErrorMessageMap = [
			fname_req : 'First Name is required.',
			emailId_req :'Email ID is required.',
			invalid_email :'Enter a valid Email ID',
			email_already_exist : 'Email ID already exists',
			pass_req :'Password is required.',
			//cnfrm_pass_req : ' Password is required.',
			pass_combination_err : 'Password should be a combination of minimum 8 characters, containing a capital letter,special character and a digit',
			enter_corr_pass : 'Enter correct password',
			pass_not_match : 'Password does not match',
			city_req :'City Name is required.',
			pin_req :'Pin code is required',
			pincode_len : 'Pin code should be of 6 digits',
			mob_req : 'Mobile Number is required',
			mob_already_exist : 'Mobile Number already exists',
			invalid_mob_no : 'Enter a valid Mobile Number',
			otp_req : 'OTP Required ',
			otp_len : 'OTP should be of 6 digits',
			org_req : 'Organization Name is required.',
			org_name_invalid : 'Enter a valid Organization Name',
			industry_req : 'Select Industry.',
			address_req : 'Address Line 1 is required.']

		//To enter data
		def static final populateFields = {browser, formData ->			
			println ("CompanyRegistrationForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i <= FIELDS.size()-1; i++){
					/*if(FIELDS[i].equals(CATEGORY) && formData[i]!= ""){
						KPCommonPage.jobCategory = formData[i]
					}*/
					if(FIELDS[i].equals(CITY) && formData[i]!= ""){
						KPCommonPage.city = formData[i]
						browser.scrollToElement(browser.getElement(Browser.XPATH, CITY))
						browser.delay(2000)
						browser.populateField(CITY,formData[i])
						browser.delay(3000)
						KPCommonPage.selectAutoComplete(browser, CITY_AUTOSELCT, formData[i].trim())
					}else if(FIELDS[i].equals(INDUSTRY)){
						if(formData[i].equals("")){
							browser.pressTab(FIELDS[i])
						}else{
							KPCommonPage.industry = formData[i]
							browser.delay(2000)
							browser.populateField(INDUSTRY,formData[i])
							browser.delay(2000)
							KPCommonPage.selectAutoComplete(browser, INDUSTRY_AUTOSELCT, formData[i].trim())
						}
					}else{
						def tagName = browser.getTagName(FIELDS[i])
						browser.scrollToElement2(FIELDS[i])
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
					}
				}
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
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, CompanyRegistrationPageErrorMessageMap)			
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}
		
		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.scrollToElement2(ORGANIZATION_NAME)
			browser.delay(500)
			browser.getValidationMessages errFields // get the validation messages from the current page.			
		}	
	}	
}
