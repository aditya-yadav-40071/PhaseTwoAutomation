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

	def static startDateVerify = { browser, params ->
		new CourseDetailPageForm().startDateVerify(browser, params)
	}

	def static endDateVerify = { browser, params ->
		new CourseDetailPageForm().endDateVerify(browser, params)
	}

	static final class CourseDetailPageForm extends WebForm {

		private static final def COURSENAME = ".//div[@class='details_list_heading']/h4"

		private static final def STARTDATE = ".//div[@class='course_status_area_detail']/div[5]/span"

		private static final def ENDDATE = ".//div[@class='course_status_area_detail']/div[6]/span"

		private static final def STARTBTN = ".//button[@class='btn btn-lg start_learn_btn kp-btn']"

		//To verify the course clicked
		def static courseName= { browser, data->
			browser.delay(2000)
			def courseNameText= browser.gettext(COURSENAME)
			if(courseNameText.equalsIgnoreCase(data[0])){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "CourseIssue", "Courses name does not match")
			}
		}

		def static startDateVerify= { browser, data->
			browser.delay(1000)
			def courseStartDate= browser.gettext(STARTDATE)
			if(courseStartDate.equalsIgnoreCase(data[0])){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "StartDateIssue", "Start Date does not match")
			}
		}

		def static endDateVerify= { browser, data->
			browser.delay(1000)
			if(browser.isDisplayed(STARTBTN)){
				def courseEndDate= browser.gettext(ENDDATE)
				if(courseEndDate.equalsIgnoreCase(data[0])){
					return new SuccessOutcome()
				}else {
					return KPCommonPage.returnFailureOutcome(browser, "EndDateIssue", "End Date does not match")
				}
			}
		}
	}
}

