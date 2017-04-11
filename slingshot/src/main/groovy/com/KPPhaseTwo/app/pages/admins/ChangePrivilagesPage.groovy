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

	def submit = { browser, formKey, formData ->
		println ("Submit method in ChangePrivilagesPage")
		new ChangePrivilagesPageForm().submit(browser, formData)
	}

	def static adminPrivilageChanged= { browser, formData ->
		new ChangePrivilagesPageForm().adminPrivilageChanged(browser,formData)
	}


	static final class ChangePrivilagesPageForm extends WebForm {

		private static final def  MANAGE_TRAINER = ".//md-checkbox[@ng-model='subadminform.changePrivilegesForTrainer']"

		private static final def  MANAGE_JOB = ".//md-checkbox[@ng-model='subadminform.changePrivilegesForHr']"

		private static final def ADMIN_TRAIN = ".//ul[@class='pull-left menu mt_40 no-padding ng-scope']/li[@ng-show='getOrgPodDashboard']"

		private static final def ADMIN_HIRE = ".//ul[@class='pull-left menu mt_40 no-padding ng-scope']/li[@ng-show='getOrgJobDashboard']"

		private static final def EDIT_TEXT = ".//a[@class='profile-edit-link display-block']/h5"

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
					KPCommonPage.Privilage=formData[i]
					def tagName= browser.getTagName(FIELDS[i])
					println "Tagname of the field"+FIELDS[i]+"is::::"+tagName
				}
				println "KPCommonPage.Privilage : " +KPCommonPage.Privilage
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
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(3000)
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		def static final adminPrivilageChanged = { browser,formData ->

			def result
			def adminPrivilages=KPCommonPage.Privilage
			for(int i=0;i<adminPrivilages.size()-1;i++){
				if(adminPrivilages[i] == "0" && adminPrivilages[i+1] == "1") {
					println "Inside Data 0 and 1"
					if(browser.isDisplayed(ADMIN_HIRE) && browser.gettext(ADMIN_HIRE).equalsIgnoreCase("Hire") && !browser.isDisplayed(ADMIN_TRAIN)){
						result=true
					}else{
						result= false
						break
					}
				}else if(adminPrivilages[i]== "1" && adminPrivilages[i+1]== "0") {
					println "Inside Data 1 and 0"
					if(browser.isDisplayed(ADMIN_TRAIN) && browser.gettext(ADMIN_TRAIN).equalsIgnoreCase("Train") && !browser.isDisplayed(ADMIN_HIRE)){
						result=true
					}else{
						result= false
						break
					}
				}else {
					println "Inside Data 1 and 1"
					def hireText=browser.gettext(ADMIN_TRAIN)
					def trainText=browser.gettext(ADMIN_HIRE)
					def profileText=browser.gettext(EDIT_TEXT)

					if(hireText.isDisplayed(ADMIN_TRAIN) && trainText.isDisplayed(ADMIN_HIRE) && !profileText.equalsIgnoreCase("Edit Organization Profile")){
						if(hireText.equalsIgnoreCase("Hire") && trainText.equalsIgnoreCase("Train")){
							result=true
						}else{
							result= false
							break
						}
					}
				}
			}
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AdminChangePrivilageIssue", "Admin Privilages does not match")

			}
		}
	}
}