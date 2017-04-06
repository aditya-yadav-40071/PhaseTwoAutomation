package com.knowledgePodium.app.pages.user

import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome
import com.knowledgePodium.web.WebForm
import com.knowledgePodium.web.WebPage
import com.knowledgePodium.tools.Browser
import com.knowledgePodium.app.pages.KPCommonPage
import java.lang.Object
import java.util.Collections


final class ClassRoomPage extends WebPage {

	def static courseVideoName ={ browser, formData ->
		new ClassRoomPageForm().courseVideoName(browser, formData)
	}

	static final class ClassRoomPageForm extends WebForm {

		private static final def VIDEO_NAME = ".//div[@class='col-md-9 col-xs-9']/h2"
		
		private static final def BRDCRMB_NAME = " .//ul[@class='breadcrumb']/li[3]/a"
		def static courseVideoName= { browser, data->
			def videoName=browser.gettext(VIDEO_NAME)
			def breadCrumbName=browser.gettext(BRDCRMB_NAME)
			if(videoName.equalsIgnoreCase(breadCrumbName)){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "NameMismatchIssue", "Video name and Breadcrumb name does not match")
			}
		}
	}
}
