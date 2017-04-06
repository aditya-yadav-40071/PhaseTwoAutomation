package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage



final class ChangePrivilagesPage extends WebPage {

	/*def static changePrivilagesforAdmin= { browser, formData->
	 new ChangePrivilagesForm().changePrivilagesforAdmin browser,formData
	 }*/

	def populateData = { browser, formKey, formData ->
		println ("Populate data method in Change Privilages Page")
		new ChangePrivilagesPageForm().populateFields(browser, formData);
	}

	def submit = {browser, formKey, formData ->
		println ("Submit method in ChangePrivilagesPage")
		new ChangePrivilagesPageForm().submit(browser, formData)
	}


	static final class ChangePrivilagesPageForm extends WebForm {
		private static final def  MANAGE_TRAINER = ".//md-checkbox[@ng-model='subadminform.changePrivilegesForTrainer']"

		private static final def  MANAGE_JOB = ".//md-checkbox[@ng-model='subadminform.changePrivilegesForHr']"

		private static final def UPDATE = ".//div[@class='row mb_20']/div/button"

		private static final def FIELDS= [MANAGE_TRAINER, MANAGE_JOB]

		private static final def FORM_ERROR = ".//span[@class='ng-binding ng-scope']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR]

		def static final changePrivilagesPageErrorMessageMap = [success_message : 'Sub-admin/Trainer/HR set privileges updated successfully.']

		def static final populateFields = { browser, formData ->
			println ("ChangePrivilagesAdminForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<FIELDS.size();i++){
				def tagName= browser.getTagName(FIELDS[i])
				println "Tagname of the field"+FIELDS[i]+"is::::"+tagName
				}
				outcome= WebForm.enterData(browser,formData,FIELDS,UPDATE,WAIT_REQ_FIELDS)
			}
			return outcome
		}

		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, changePrivilagesPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(3000)
			} 
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
	}
}


