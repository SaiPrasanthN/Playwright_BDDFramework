package com.learnings.stepdefinitions;

import com.learnings.base.BaseClass;
import com.microsoft.playwright.Page;

import io.cucumber.java.en.Given;

public class IntialStepDefintions  extends BaseClass{
	
	
	@Given("I am on login Page")
	public void i_am_on_login_page() {
	  Page page=loadPageObject();
	String title=  page.title();
	  System.out.println("Title is " + title);
	}

	@Given("^login with (.+) and (.+)$")
	public void login_with_prashanth_and_prashanth(String userName,String password) {
	    System.out.println("Test");
	}

	@Given("verifies if user is on homePage")
	public void verifies_if_user_is_on_home_page() {
		System.out.println("Test");
	}


}
