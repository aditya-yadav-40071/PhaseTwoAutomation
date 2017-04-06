package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage
import java.lang.Object
import java.util.Collections


final class CourseDetailPage extends WebPage {

	def static courseName = { browser, params ->
		new CourseDetailPageForm().courseName(browser, params)
	}

	def static courseCompletionStatement = { browser, params ->
		new CourseDetailPageForm().courseCompletionStatement(browser, params)
	}

	def static courseDetails = { browser, params ->
		new CourseDetailPageForm().courseDetails(browser, params)
	}

	static final class CourseDetailPageForm extends WebForm {

		private static final def COURSENAME = ".//div[@class='details_list_heading']/h4"

		private static final def STARTDATE = ".//div[@class='course_status_area_detail']/div[5]/span"

		private static final def ENDDATE = ".//div[@class='course_status_area_detail']/div[6]/span"

		private static final def STARTBTN = ".//button[@class='btn btn-lg start_learn_btn kp-btn']"

		private static final def COURSECOMPLETESTATEMENT = ".//span[@class='details_list_titles ng-binding']"

		private static final def COURSEDETAILS = "//div[button[text()='Start/Continue']]/following-sibling::div//span"

		//To verify the course clicked
		def static courseName= { browser, data->
			browser.delay(2000)
			def courseNameText= browser.gettext(COURSENAME)
			def commonCourseName=KPCommonPage.COURSENAME
			if(courseNameText.equalsIgnoreCase(commonCourseName)){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "CourseIssue", "Courses name does not match")
			}
		}

		def static courseCompletionStatement= { browser, data->
			def uponCourseCompletion= browser.gettext(COURSECOMPLETESTATEMENT)
			if(uponCourseCompletion.equalsIgnoreCase(data[0])){
				println "Completion statement matched"
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "CourseCompletionStatementIssue", "Course Completion Statement does not match")
			}
		}

		def static courseDetails = { browser, data->
			def detailsofcourse=browser.getLists(COURSEDETAILS)
			println "detailsofcourse::"+detailsofcourse
			println "Data::"+data
			if(data?.sort(false) == detailsofcourse?.sort(false)){
				println "Data matched"
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "DataMismatchIssue", "Data does not match")
			}
		}
	}
}

