package com.knowledgePodium.app.pages

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import com.knowledgePodium.model.FailureOutcome
import com.knowledgePodium.model.SuccessOutcome


/**
 * @author Sandhya
 * Date: 24/7/2014
 * Common actions 
 */
class KPCommonPage {

	private static def RADIOID = ".//input[@value='radioValue'][@name='radioName']"

	private static def RADIO_BTN_XPATH1 = ".//input[@type='radio']"

	private static final def FIRSTBUTTON = ".//a[text()='First']"

	private static final def NEXTBUTTON = ".//a[text()='Next']"

	private static final def  LASTBUTTON = "//li[@class='pagination-last ng-scope']/a"

	private static final def PAGECOUNT = ".//li[@class='pagination-page ng-scope active']/a"
	
	private static def COURSENAME 


	//To return Failure outcome
	static def returnFailureOutcome(def browser, def fileName, def message){
		println message
		browser.takeScreenShot(fileName)
		return new FailureOutcome(message)
	}

	//get coloumns from excel filed which has data
	static def getFinalFeilds(def data, def FEILDS){
		def formFeild = []
		for(int i =0; i<= data.size()-1; i++){
			if(data[i] != ""){
				formFeild.add(FEILDS[i])
			}
		}
		formFeild
	}

	//get coloumns from excel filed which has data
	static def getFinalData(def data, def FIELDS){
		def formData = []
		for(int i =0; i<= data.size()-1; i++){
			if(data[i] != ""){
				formData.add(data[i])
			}
		}
		formData
	}

	//To get Radio Button Id
	static def getRadioButtonField(def browser, def field, def data){
		def radioId
		def radioValue = browser.getLists(RADIO_BTN_XPATH1, "value")
		def radioNames = browser.getLists(RADIO_BTN_XPATH1, "name")
		println "radioName :" +radioNames
		for(int i=0; i<= radioValue.size() - 1; i++){
			println "data.size() :"+data.size()
			for(int j=0;j<data.size();j++){
				if(radioValue[i].trim().equalsIgnoreCase(data[j].trim())){
					def value = radioValue[i].trim()
					radioId = RADIOID.replace("radioValue", value)
				}
				for(int k=0;k<=radioNames.size() -1; k++){
					def name = radioNames[k].trim()
					if(field.contains(name)){
						name = radioNames[k].trim()
						radioId = radioId.replace("radioName", name)
						break
					}
				}
				break
			}
		}
		radioId
	}

	static def paginition(def browser){
		def pageCount=1
		def courseText
		def courseTextList = []
		def checkIfEnabled=browser.checkEnabled(LASTBUTTON)
		if(checkIfEnabled==true ) {
			browser.delay(1000)
			browser.click(LASTBUTTON)
			browser.delay(2000)
			def pageCountText=browser.gettext(PAGECOUNT)
			pageCount=Integer.parseInt(pageCountText)
			browser.click(FIRSTBUTTON)
			browser.delay(3000)
		}
		return pageCount
	}

	static def checkNext(def browser){
		def checkEnabledNxt= browser.checkEnabled(NEXTBUTTON)
		if(checkEnabledNxt==true){
			browser.click(NEXTBUTTON)
			browser.delay(2000)
		}
	}
}

