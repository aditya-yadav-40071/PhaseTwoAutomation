package com.KPPhaseTwo.app.pages

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome


/**
 * @author Samir
 * Date: 20/2/2017
 * Common actions 
 */
class KPCommonPage {

	private static def RADIOID = ".//*[@name='radioName'][@value='radioValue']"

	private static def RADIO_BTN_XPATH1 = ".//input[@type='radio']"

	//Registration
	private static def city,industry

	//Login
	private static def userName,passWord

	//Pods
	private static def levelLists, industryLists, podName, firstPodName

	private static def adminPassword

	//private static def adminEmailId
	
	private static def adminEmailId = []

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
		for(int i=0; i<= radioValue.size() - 1; i++){
			if(radioValue[i].trim().equalsIgnoreCase(data.trim())){
				def value = radioValue[i].trim()
				radioId = RADIOID.replace("radioValue", value)
				for(int j=0;j<=radioNames.size() -1; j++){
					def name = radioNames[j].trim()
					if(field.contains(name)){
						name = radioNames[j].trim()
						radioId = radioId.replace("radioName", name)
						break
					}
				}
				break
			}
		}
		radioId
	}


	//To select from the auto complete
	static def selectAutoComplete(def browser, def autoCompleteList, def dataToSelect){
		def xpathToSelect
		def lists = browser.getLists(autoCompleteList)
		xpathToSelect = browser.getListElements(autoCompleteList)
		for(int i =0; i<= lists.size()-1;i++){
			if(lists[i].trim().equalsIgnoreCase(dataToSelect.trim())){
				browser.delay(1500)
				browser.clickElement(xpathToSelect[i])
				browser.delay(1500)
				break
			}
		}
	}

	//to generate unique email address
	public static def  generateRandomEmailAddress(def data){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		data = new StringBuilder(data).insert(data.indexOf("@"), randomInt).toString();
		return data;
	}

	//to generate unique phone no up to 10 digits
	public static def generateRandomCellNo(){
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return number;
	}

}
