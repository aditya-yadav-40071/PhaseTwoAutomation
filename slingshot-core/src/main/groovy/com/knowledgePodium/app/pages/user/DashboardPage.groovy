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

	def static courseNameMatch = { browser, params ->
		new DashboardPageForm().courseNameMatch(browser, params)
	}

	static final class DashboardPageForm extends WebForm {

		private static final def XPATHLISTOFCOURSES= ".//h2[@class='primary_title mb_15 height_30 cursor_pointer ng-binding']"

		private static final def FIRSTBUTTON = "html/body/div[1]/div[2]/div[2]/div[2]/ul/li[1]/a"

		private static final def NEXTBUTTON = "html/body/div[1]/div[2]/div[2]/div[2]/ul/li[5]/a"

		private static final def  LASTBUTTON = "//li[@class='pagination-last ng-scope']/a"

		private static final def PAGECOUNT = ".//li[@class='pagination-page ng-scope active']/a"

		def static clickCourseName= { browser, data->
			boolean result=false
			def pageCount=1
			def checkIfEnabled=browser.checkEnabled(LASTBUTTON)
			if(checkIfEnabled==true){
				browser.click(LASTBUTTON)
				browser.delay(1000)
				def value=browser.gettext(PAGECOUNT)
				pageCount=Integer.parseInt(value)
				browser.click(FIRSTBUTTON)
				browser.delay(1000)
			}
			for(int i=0;i<pageCount;i++){
				def coursesXpath=browser.getListElements(XPATHLISTOFCOURSES)
				def coursesText=browser.getLists(XPATHLISTOFCOURSES)
				if(coursesText.size()>0){
					for(int k=0; k<coursesText.size(); k++){
						if(coursesText[k].equalsIgnoreCase(data[0])){
							browser.clickElement(coursesXpath[k])
							browser.delay(3000)
							result= true
							break;
						}
					}
				}else {
					println "No Courses found on the page"
				}
				def checkEnabledNext= browser.checkEnabled(NEXTBUTTON)
				if(checkEnabledNext==true){
					browser.click NEXTBUTTON
					browser.delay(2000)
				}
			}
			if(result){
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "ClickIssue", "The course specified was not found on the page")
			}
		}

		def static courseNameMatch = { browser, data->
			def pageCount=0
			def courseText
			def courseTextList = []
			def dataList
			def checkIfEnabled=browser.checkEnabled(LASTBUTTON)
			if (data[0].contains(",")) {
				dataList=data[0].split(",")
			}else {
				println "Data provided does not have the specified character to split"
			}
			if(checkIfEnabled==true ) {
				browser.click(LASTBUTTON)
				browser.delay(1000)
				def value=browser.gettext(PAGECOUNT)
				pageCount=Integer.parseInt(value)
				browser.click(FIRSTBUTTON)
				browser.delay(1000)
			}
			for(int i=0; i<pageCount; i++){
				courseText=browser.getLists(XPATHLISTOFCOURSES)
				browser.delay(2000)
				courseTextList.addAll(courseText)
				def checkEnabledNxt= browser.checkEnabled(NEXTBUTTON)
				if(checkEnabledNxt==true){
					browser.click(NEXTBUTTON)
					browser.delay(2000)
				}
			}
			println "courseTextList:::"+courseTextList
			if(dataList?.sort(false) == courseTextList?.sort(false)){
				println "Data matched"
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "DataMismatchIssue", "Data does not match")
			}
		}
	}
}
