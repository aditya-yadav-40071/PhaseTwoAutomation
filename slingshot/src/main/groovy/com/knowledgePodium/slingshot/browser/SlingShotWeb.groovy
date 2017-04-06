package com.knowledgePodium.slingshot.browser

import groovy.util.logging.Log4j;

import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities

import org.openqa.selenium.safari.SafariOptions
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.UnexpectedAlertBehaviour
import org.openqa.selenium.remote.CapabilityType


@Log4j
class SlingShotWeb {

    def browser
	
	def browserName
	
	def appUrl
	
    public SlingShotWeb() {
		
        def props = new Properties()
        new File("configuration/Application_config.properties").withInputStream { stream ->
            props.load(stream)
        }		
		
		browserName = "$props.browserName"
		//For running on local machine
		if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "F:/KPCloudSiteConsumer/Drivers/chromedriver.exe");			
		}else if(browserName.equalsIgnoreCase("internetExplorer")){
			System.setProperty("webdriver.ie.driver", "E:/automation/KPSiteConsumer/Drivers/InternetExplorerDriver.exe");		
		}
		browser = new com.knowledgePodium.tools.Browser(browserName, [maximize: true])		
		
		appUrl = "$props.APP_URL"
    }	
	
	public initialize() {
		browser.openUrl(appUrl)
	}
	
	public reset() {
		log.info("Resetting browser to " + appUrl);
		browser.deleteCookies()
		browser.openUrl(appUrl)
	}	
	
	// To take screenshot
	public takeScreenShot(fileName){
		browser.takeScreenShot(fileName)
	}
	//To close browser
	public close(){
		log.info("Closing the browser")
		browser.close()
	}

}
