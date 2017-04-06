package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage
import java.lang.Object
import java.util.Collections


final class DashboardPage extends WebPage {

	def static clickCourseName ={ browser, formData ->
		new DashboardPageForm().clickCourseName(browser, formData)
	}

	def static coursesEnrolled = { browser, params ->
		new DashboardPageForm().coursesEnrolled(browser, params)
	}

	static final class DashboardPageForm extends WebForm {

		private static final def LISTOFCOURSES= ".//h2[@class='primary_title mb_15 height_30 cursor_pointer ng-binding']"

		def static clickCourseName= { browser, data->
			boolean result=false
			def pageCount
			pageCount = KPCommonPage.paginition(browser)
			for(int i=0;i<pageCount;i++){
				def coursesXpath=browser.getListElements(LISTOFCOURSES)
				def coursesText=browser.getLists(LISTOFCOURSES)
				if(coursesText.size()>0){
					for(int k=0; k<coursesText.size(); k++){
						if(coursesText[k].equalsIgnoreCase(data[0])){
							KPCommonPage.COURSENAME = coursesText[k]
							browser.clickElement(coursesXpath[k])
							browser.delay(3000)
							result= true
							break;
						}
					}
				}else {
					println "No Courses found on the page"
				}
				KPCommonPage.checkNext(browser)
			}
			if(result){
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ClickIssue", "The course specified was not found on the page")
			}
		}

		def static coursesEnrolled = { browser, data->
			def pageCount
			def courseText
			def courseTextList = []
			def dataList
			if (data[0].contains(",")) {
				dataList=data[0].split(",")
			}else {
				dataList=data[0]
			}
			pageCount = KPCommonPage.paginition(browser)
			for(int i=0; i<pageCount; i++){
				courseText=browser.getLists(LISTOFCOURSES)
				browser.delay(2000)
				courseTextList.addAll(courseText)
				KPCommonPage.checkNext(browser)
			}
			if(dataList?.sort(false) == courseTextList?.sort(false)){
				println "Data matched"
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "DataMismatchIssue", "Data does not match")
			}
		}
	}
}