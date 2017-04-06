package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 06/04/2017
 */

final class PostJobPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new PostJobForm().populateFields(browser, formData);
	}
	
	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in CompanyRegistrationPage")
		new PostJobForm().submit(browser, formData)
	}
	
	static final class PostJobForm extends WebForm {

		//CompanyRegistration form elements
		private static final def JOB_TITLE = ".//input[@id='jobTitle']"

		private static final def NO_OF_POSITIONS = ".//input[@id='noOfPositions']"

		private static final def MIN_AGE = ".//input[@id='minAge']"
		
		private static final def MAX_AGE = ".//input[@id='maxAge']"
		
		private static final def MIN_EXPERIENCE = ".//*[@id='minExperience']"
		
		private static final def MAX_EXPERIENCE = ".//*[@id='maxExperience']"
		
		private static final def MIN_SALARY = ".//*[@id='minSalary']"
		
		private static final def MAX_SALARY = ".//*[@id='maxSalary']"
		
		private static final def INDUSTRY = ".//*[@id='jobIndustry']"
		
		private static final def LOCATION = ".//*[@id='jobLocation']"
		
		private static final def JOB_TYPE = ".//select[@name='jobType']"
		
		private static final def JOB_LAST_DATE = ".//*[@id='jobLastDate']"
		
		private static final def JOB_POSTING_DATE = ".//*[@id='jobPostingDate']"
		
		private static final def JOB_EXPIRY_DATE = ".//*[@id='jobExpiryDate']"
		
		private static final def EDU_QUALIFICATION = ".//input[@name='multipleSelectEducation']"
		
		private static final def SKILLS = ".//input[@name='multipleSelectSkills']"
		
		private static final def PRE_CERTIFICATE = ".//input[@name='multipleSelectCertificates']"
		
		private static final def JOB_DESCRIPTION = ".//*[@id='jobDescription']"
		
		private static final def ADD_BUTTON = ".//button[text()='Add']"
		
		private static final def FIELDS = [JOB_TITLE, NO_OF_POSITIONS, MIN_AGE, MAX_AGE, MIN_EXPERIENCE,MAX_EXPERIENCE,MIN_SALARY,MAX_SALARY, INDUSTRY, LOCATION, JOB_TYPE, JOB_LAST_DATE, JOB_POSTING_DATE, JOB_EXPIRY_DATE, EDU_QUALIFICATION, SKILLS, PRE_CERTIFICATE, JOB_DESCRIPTION]
		
		// the error fields.
		private static final def FORM_ERROR = ""
		
		private static final def FIELD_ERROR_1 = ".//span[@class='error_message']"
		
		private static final def FIELD_ERROR_2 = ".//p[@class='error_message']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR_1,FIELD_ERROR_2]
		
		//error message map (Key-Value Pair)
		def static final CompanyRegistrationPageErrorMessageMap = [
			jobtitle_req : 'Job Title is required.',
			noofpositions_req :' No of positions is required.',
			minage_req :'Min age is required.',
			maxage_req : 'Max age is required.',
			minexperience_req :'Min experience is required.',
			maxexperience_req : 'Max experience is required.',
			minsalary_req : 'Min salary is required.',
			maxsalary_req : 'Max salary is required.',
			industry_req : 'Industry is required',
			location_req : 'Location is required.',
			jobtype_req : 'Job Type is required',
			joblastdate_req : 'Job last date is required.',
			jobpostdate_req :'Date of Job posting is required.',
			jobexpirydate_req :'Expiry date of Job posting required.',
			eduqualification_req : 'Education Qualification is required',
			skill_req : 'Skills is required',
			jobdesc_req : 'Job Description is required.',
			lastAfterPosting_date : 'Last date of application should be after Date of Job Posting',
			//lastAfterExpiry_date : 'Last date of application can't be after Expiry Date of Job Posting',
			postingBeforeExpiry_date : 'Date of Job Posting should be before Expiry of Job Posting',
			jobpostingBeforeLast_date : 'Date of Job Posting should be before Last Date of Job Application',
			expiryAfterPosting_date : 'Expiry of Job Posting should be after Date of Job Posting',
			expiryAfterLast_date : 'Expiry of Job Posting should be after Last Date of job Application']

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
