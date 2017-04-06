package com.knowledgePodium.app.dsl

import com.knowledgePodium.app.pages.user.SiteSignInPage
import com.knowledgePodium.app.pages.user.DashboardPage
import com.knowledgePodium.app.pages.user.CourseDetailPage
import com.knowledgePodium.app.pages.user.ClassRoomPage

/**
 * Created by Sandhya on 27/9/2015
 */
class UserManager {

	//to verify the username of the logged user
	def consumerName = { browser, params ->
		println "Inside usermanager consumerName"
		SiteSignInPage.consumerName browser, params
	}

	def clickCourseName = { browser, params ->
		println "Inside usermanager clickCourseName"
		DashboardPage.clickCourseName browser, params
	}

	def coursesEnrolled = { browser, params ->
		println "Inside usermanager coursesEnrolled"
		DashboardPage.coursesEnrolled browser, params
	}

	def courseName = { browser, params ->
		println "Inside usermanager courseName"
		CourseDetailPage.courseName browser, params
	}
	
	def courseCompletionStatement = { browser, params ->
		println "Inside usermanager courseCompletionStatement"
		CourseDetailPage.courseCompletionStatement browser, params
	}

	def courseDetails = { browser, params ->
		println "Inside usermanager courseDetails"
		CourseDetailPage.courseDetails browser, params
	}

	def courseVideoName = { browser, params ->
		println "Inside usermanager courseVideoName"
		ClassRoomPage.courseVideoName browser, params
	}

	
}