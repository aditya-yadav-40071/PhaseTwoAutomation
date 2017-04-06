package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage
import java.lang.Object
import java.util.Collections


final class LearningTrackPage extends WebPage {

	def static clickLearningTrack ={ browser, formData ->
		new LearningTrackPageForm().clickLearningTrack(browser, formData)
	}

	def static learningTrackEnrolled = { browser, formData ->
		new LearningTrackPageForm().learningTrackEnrolled(browser, formData)
	}

	static final class LearningTrackPageForm extends WebForm {

		private static final def LISTOFLT= ".//h2[@class='primary_title mb_15 height_30 cursor_pointer ng-binding']"

		private static final def LISTOFCOURSES= ".//h2[@class='primary_title mb_15 height_30 cursor_pointer ng-binding']"

		private static def COURSENROLLEDCOUNT = "//h2[contains(text(),'LtName')]/following-sibling::span[2]"


		def static clickLearningTrack = { browser, data->
			boolean result=false
			def pageCount = KPCommonPage.paginition(browser)
			for(int i=0;i<pageCount;i++){
				def ltXpath=browser.getListElements(LISTOFLT)
				def ltText=browser.getLists(LISTOFLT)
				if(ltText.size()>0){
					for(int k=0; k<ltText.size(); k++){
						if(ltText[k].equalsIgnoreCase(data[0])){
							COURSENROLLEDCOUNT=COURSENROLLEDCOUNT.replace('LtName', data[0])
							def courseCount=browser.gettext(COURSENROLLEDCOUNT)
							def courseCountValue=Integer.parseInt(courseCount)
							browser.clickElement(ltXpath[k])
							browser.delay(4000)
							def coursesText=browser.getLists(LISTOFCOURSES)
							if(coursesText.size()==courseCountValue){
								return new SuccessOutcome()
							}else {
								return KPCommonPage.returnFailureOutcome(browser, "CourseCountMatchIssue", "The count of the courses does not match")
							}
							result= true
							break;
						}
					}
				}else {
					println "No Learning Track found on the page"
				}
				KPCommonPage.checkNext(browser)
			}
			if(result){
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ClickIssue", "The Learning Track specified was not found on the page")
			}
		}

		def static learningTrackEnrolled = { browser, data->
			def ltText
			def ltTextList = []
			def dataList
			if (data[0].contains(",")) {
				dataList=data[0].split(",")
			}else {
				dataList=data[0]
			}
			def pageCount = KPCommonPage.paginition(browser)
			for(int i=0; i<pageCount; i++){
				ltText=browser.getLists(LISTOFLT)
				if(ltText.size()>0){
					browser.delay(2000)
					ltTextList.addAll(ltText)
				}else{
					println "No learning track found on the page"
				}
				KPCommonPage.checkNext(browser)
			}
			println "ltTextList:::"+ltTextList
			if(dataList?.sort(false) == ltTextList?.sort(false)){
				println "Data matched"
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "DataMismatchIssue", "Data does not match")
			}
		}
	}
}
