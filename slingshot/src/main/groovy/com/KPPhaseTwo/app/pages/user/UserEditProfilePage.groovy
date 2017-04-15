package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 06/04/2017
 */


final class UserEditProfilePage extends WebPage {

	//Override
	def populateData = { browser, formKey, formData ->
		println "Populte data"
		new UserEditProfileForm().populateFields(browser, formData);
	}

	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in LoginPage")
		new UserEditProfileForm().submit(browser, formData)
	}



	static final class UserEditProfileForm extends WebForm {

		//CompanyRegistration form elements

		private static final def FIRST_NAME         = ".//input[@id='fname']"

		private static final def MIDDLE_NAME        = ".//input[@id='mname']"

		private static final def LAST_NAME          = ".//input[@id='lname']"

		private static final def AGE                = ".//input[@id='age_new']"

		private static final def GENDER             = ".//select[@id='gender_new']"

		private static final def CAL_BTN            =  ".//*[@id='dobdatepicker']" //".//span[@class='input-group-btn vertical-align-top']/button"

		private static final def SELECT_DOB         = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a"

		private static final def MARITAL_STATUS     = ".//select[@name='marital_new']"

		private static final def ADD_LINE_1         = ".//input[@id='address1']"

		private static final def ADD_LINE_2         = ".//input[@id='address2']"

		private static final def ADD_LINE_3         = ".//input[@id='address3']"

		private static final def CITY               = ".//input[@id='city']"

		private static final def CITY_AUTOSELCT     = ".//div[@class='pac-container pac-logo'][last()]/descendant::span[@class='pac-matched']"

		private static final def PIN_CODE           = ".//input[@name='pincode']"

		private static final def EMAIL_ID           = ".//input[@id='emailId']"

		private static final def MOBILE_NUM         = ".//input[@id='mobileno']"

		private static final def SEND_OTP_BTN       = ".//div[@class='col-md-3']/button[@class='full-width button-secondary btn-small']"

		private static final def RESEND_OTP         = ".//div[@class='col-md-3']/h4[@class='col-md-12 link-secondary pt_13']"

		private static final def OTP_TEXTBOX        = ".//input[@id='otp']"

		private static final def X_PERCENTAGE       = ".//input[@id='xPercentage']"

		private static final def X_SCHOOL_NAME      = ".//input[@id='xSchoolName']"

		private static final def X_YEAR_PASS        = ".//div[@ng-init='getYears(-46,46)']/select[@name='xYearOfPass']"

		private static final def XII_PERCENTAGE     = ".//input[@id='xiiPercentage']"

		private static final def XII_SCHOOL_NAME    = ".//input[@id='xiiSchoolName']"

		private static final def XII_YEAR_PASS      = ".//div[@ng-init='getYears(-46,46)']/select[@name='xiiYearOfPass']"

		private static final def DIPLOMA_PERCENTAGE = ".//input[@id='diplomaPercentage']"

		private static final def DIPLOMA_COLLEGE    = ".//input[@id='diplomaCollegeName']"

		private static final def DIPLOMA_COURSE     = ".//input[@id='diplomaCourseName']"

		private static final def DIPLOMA_START      = ".//div[@ng-init='getYears(-46,46)']/select[@name='diplomaStartYear']"

		private static final def DIPLOMA_END        = ".//div[@ng-init='getYears(-46,46)']/select[@name='diplomaEndYear']"

		private static final def UG_PERCENTGE       = ".//input[@id='undergraduatePercentage']"

		private static final def UG_COLLEGE         = ".//input[@id='undergraduateSchoolName']"

		private static final def UG_FIELD           = ".//input[@id='undergraduateFieldName']"

		private static final def UG_START           = ".//div[@ng-init='getYears(-46,46)']/select[@name='undergraduateStartYear']"

		private static final def UG_END             = ".//div[@ng-init='getYears(-46,46)']/select[@name='undergraduateEndYear']"

		private static final def PG_PERCENTGE       = ".//input[@id='postgraduatePercentage']"

		private static final def PG_COLLEGE         = ".//input[@id='postgraduateSchoolName']"

		private static final def PG_SPECILIZATION   = ".//input[@id='specialization']"

		private static final def PG_START           = ".//div[@ng-init='getYears(-46,46)']/select[@name='postgraduateStartYear']"

		private static final def PG_END             = ".//div[@ng-init='getYears(-46,46)']/select[@name='undergraduateEndYear']"

		private static final def INDUSTRY           = ".//select[@name='industryId']"

		private static final def JOB_ROLE           = ".//input[@id='jobRole_new']"

		private static final def COMPANY_NAME       = ".//input[@id='companyName_new']"

		private static final def WE_START_MONTH     = ".//select[@name='startmonth']"

		private static final def WE_START_YEAR      = ".//select[@name='startyear']"

		private static final def WE_END_MONTH       = ".//select[@name='endmonth']"

		private static final def WE_END_YEAR        = ".//select[@name='endyear']"

		private static final def WORK_CHECKBOX      = ".//md-checkbox[@aria-label='Checkbox']/div[@class='md-container md-ink-ripple']"

		private static final def WE_ADD_MORE        = ".//div[@class='col-md-3 col-sm-6 col-xs-6 mb_20']/button[@class='btn button-secondary']"

		private static final def SKILLS             = ".//input[@name='multipleSelectSkills']"

		private static final def CHOOSE_BTN         = ".//div[@class='mb_10 ng-binding']/label[@class='button-pri']"

		private static final def CERTIFICATE_NAME   = ".//md-autocomplete-wrap[@class='md-whiteframe-z1']"

		private static final def CERTI_ADD_MORE     = ".//div[@class='col-md-3 col-sm-6 col-xs-6']/div"

		private static final def UPDATE_BUTTON      = ".//div[@class='row mt20 mb20']/div/button"

		//private static final def FIELDS = [FIRST_NAME, MIDDLE_NAME, LAST_NAME, AGE, GENDER, CAL_BTN, MARITAL_STATUS, ADD_LINE_1, ADD_LINE_2, ADD_LINE_3, CITY, PIN_CODE, EMAIL_ID, MOBILE_NUM]

		private static final def FIELDS = [CAL_BTN]
		// the error fields.
		private static final def FORM_ERROR = ".//span[@class='ng-binding ng-scope']"

		private static final def FIELD_ERROR_1 = ".//span[@class='error_message']"

		private static final def FIELD_ERROR_2 = ".//p[@class='error_message']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR_1]

		//error message map (Key-Value Pair)
		def static final PostJobPageErrorMessageMap = [
			fname_req            :  'First name is required.',
			age_req              :  'Age is required.',
			gender_req           :  'Select Gender.',
			dob_req              :  'Date Of Birth is required.',
			maritalstatus_req    :  'Select Marital Status.',
			addline1_req         :  'Address Line 1 is required.',
			city_req             :  'City Name is required.',
			pin_req              :  'Pin code is required',
			emailid_req          :  'Email ID is required.',
			invalid_emailid      :  'Not a valid Email ID',
			emailid_exists       :  'Email ID already exists',
			mobile_req           :  'Mobile Number is required',
			mobile_exist         :  'Mobile Number already exists',
			invalid_mobile       :  'Enter a valid Mobile Number',
			percent_req          :  'Percentage is required',
			invalid_percent      :  'Percentage is invalid.Use ex: 40, 56.23',
			schoolname_req       :  'School Name is required',
			year_req             :  'Year is required',
			collegename_req      :  'College Name is required',
			coursename_req       :  'Course Name is required',
			startyear_req        :  'Start Year is required',
			endyear_req          :  'End Year is required',
			endyear_beforestart  :  'End Year can\'t be Before Start Year',
			startendyear_same    :  'Start Year and End Year can\'t be Same',
			nameofcoll_req       :  'Name of the College is required',
			fieldname_req        :  'Field Name is required',
			specilization_req    :  'Specialization is required',
			startgreater_endtime :  'Start Time Period cannot be greater than End Time Period',
			startend_periodsame  :  'Start Time Period cannot be same as End Time Period',
			invalidimg_file	     :  'Not a valid file please upload supported file ex: doc, docx, pdf, rtf, jpeg, png ,jpg',
			invalid_size         :  'File size should less then 2 MB.' ]

		//To enter data
		def static final populateFields = { browser, formData ->

			println ("PostJobForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i <= FIELDS.size()-1; i++){
					/*def tagName = browser.getTagName(FIELDS[i])
					if(FIELDS[i].equals(CAL_BTN) ){
						if(formData[i] !=""){
							WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
						} else {
							browser.click FIELDS[i]
						}
					}else if(FIELDS[i].equals(CITY) ){
						println "Tag Name of the field::"+tagName
						browser.scrollToElement(browser.getElement(Browser.XPATH, FIELDS[i]))
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
						KPCommonPage.selectAutoComplete(browser, CITY_AUTOSELCT, formData[i].trim())
					}else{
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
						if(FIELDS[i].equals(MOBILE_NUM)&& formData[i]=="" ){
							browser.pressTab(MOBILE_NUM)
						}
					}
				}*/
				
				if(FIELDS[i].equals(CAL_BTN) ){
					def tagName= browser.getTagName(FIELDS[i])
					WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
				//KPCommonPage.datePicker(browser,formData[i])
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

			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, PostJobPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			println "Inside submit"
			browser.scrollToElement(browser.getElement(Browser.XPATH, submitButton))
			browser.delay(1000)
			println "Inside submit after scoll 2"
			browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_NAME))
			println "Inside submit after scoll"
			browser.delay(1000)
			println " 1::: "+browser.checkEnabled(submitButton)
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(1000)
			}
			println "after submit"
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
	}
}
