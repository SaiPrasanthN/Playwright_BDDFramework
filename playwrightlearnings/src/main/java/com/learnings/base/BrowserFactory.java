package com.learnings.base;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {
	
public static 	Browser browser;
public static 	Page page;
	LaunchOptions launchOptions=new LaunchOptions();
	
	
	public void loadBrowser(String browserName) {
	
		Playwright playwright=Playwright.create();
		launchOptions.setHeadless(false);
		if(browserName.equalsIgnoreCase("Chrome")) {
			launchOptions.setChannel("chrome");
			
			browser=playwright.chromium().launch(launchOptions);
		}else if(browserName.equalsIgnoreCase("Firefox")) {
			browser=playwright.firefox().launch(launchOptions);
		}else if(browserName.equalsIgnoreCase("Safari")) {
			browser=playwright.webkit().launch(launchOptions);
		}
		
		page=browser.newPage();
		
		
	}
	
	
	public Page loadPageObject() {	
		return page;
		
	}
	

	
	

}


