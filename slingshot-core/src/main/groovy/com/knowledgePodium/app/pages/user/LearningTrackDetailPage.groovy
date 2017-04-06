package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage
import java.lang.Object
import java.util.Collections


final class LearningTrackDetailPage extends WebPage {

	def static learningTrackName = { browser, params ->
		new LearningTrackDetailPageForm().learningTrackName(browser, params)
	}

	def static courseEnrolled = { browser, params ->
		new LearningTrackDetailPageForm().courseEnrolled(browser, params)
	}

	def static clickCourse ={ browser, params ->
		new LearningTrackDetailPageForm().clickCourse(browser, params)
	}
	
	def static learningTrackDetails ={ browser, params ->
		new LearningTrackDetailPageForm().learningTrackDetails(browser, params)
	}
	
	def static ltCompletionStatement ={ browser, params ->
		new LearningTrackDetailPageForm().ltCompletionStatement(browser, params)
	}


	static final class LearningTrackDetailPageForm extends WebForm {

		private static final def LEARNTRACKNAME = ".//div[@class='details_list_heading']/h4"

		private static final def LISTOFCOURSES= ".//h2[@class='primary_title mb_15 height_30 cursor_pointer ng-binding']"

		private static final def LEARNINGTRACKDETAILS = ".//div[@class='course_status_area_parent col-md-3']/div/div[2]/div/span"

		private static final def LTCOMPLETESTATEMENT = ".//span[@class='details_list_titles ng-binding']"
		
		//To verify the course clicked
		def static learningTrackName = { browser, data->
			browser.delay(2000)
			def ltNameText= browser.gettext(LEARNTRACKNAME)
			if(ltNameText.equalsIgnoreCase(data[0])){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "LearningTrackNameIssue", "Learning Track name does not match")
			}
		}

		def static learningTrackDetails = { browser, data->
			def detailsoflt=browser.getLists(LEARNINGTRACKDETAILS)
			println "detailsoflt::"+detailsoflt
			println "Data::"+data
			if(data?.sort(false) == detailsoflt?.sort(false)){
				println "Data matched"
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "DataMismatchIssue", "Data does not match")
			}
		}

		def static clickCourse = { browser, data->
            boolean result=false
			def courseText=browser.getLists(LISTOFCOURSES)
			def courseXpath=browser.getListElements(LISTOFCOURSES)
			for(int i=0;i<courseText.size();i++){
				if(courseText[i].equalsIgnoreCase(data[0])){
					browser.clickElement(courseXpath[i])
					browser.delay(1000)
					return new SuccessOutcome()
					result= true
					break;
				}else {
					println "The course was not found on the page"
				}
			}
			if(result){
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ClickIssue", "The course specified was not found on the page")
			}
		}

		def static courseEnrolled= { browser, data->

			def dataList = []
			def ltTextList=browser.getLists(LISTOFCOURSES)
			if (data[0].contains(",")) {
				def splitData=data[0].split(",")
				for(int i=0;i<=splitData.size()-1;i++){
					dataList.add(splitData[i])
				}
			}else {
				dataList.add(data[0])
			}
			if(dataList?.sort(false) == ltTextList?.sort(false)){
				println "Data matched"
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "DataMismatchIssue", "Data does not match")
			}
		}
		
		def static ltCompletionStatement= { browser, data->
			def uponLtCompletion= browser.gettext(LTCOMPLETESTATEMENT)
			if(uponLtCompletion.equalsIgnoreCase(data[0])){
				println "Completion statement matched"
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "CourseCompletionStatementIssue", "Course Completion Statement does not match")
			}
		}
	}
}

