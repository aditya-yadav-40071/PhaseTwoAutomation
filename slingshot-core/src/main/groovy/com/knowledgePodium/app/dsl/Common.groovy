package com.knowledgePodium.app.dsl


import com.knowledgePodium.app.factory.WebPageFactory
import com.knowledgePodium.app.pages.PageDefs
import com.knowledgePodium.model.SuccessOutcome

/**
 * @author Sandhya
 * Date: 27/9/2015
 * Common actions 
 */
class Common {
	
	/**
	 * Returns current page (Object of type WebPage - or it's sub-type)
	 */
	public getCurrentPage (def browser) {
		def pageKey = PageDefs.getCurrentPageKey(browser);
		println ("Current Page Key: " + pageKey)
		return WebPageFactory.createWebPage(pageKey);
	}
	
	/**
	 * Perform click action for all clicks in app
	 * @param browser
	 * @param clickKey - key of the click action
	 * @return
	 */
	public clickAction (def browser, def clickKey) {
		def Outcome = ClickAction.performClick (browser, clickKey);
		return Outcome
	}
	
}
