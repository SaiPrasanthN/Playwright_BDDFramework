package com.learnings.base;

import java.util.Objects;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.learnings.listener.ExtentReportsClass;

import io.cucumber.testng.TestNGCucumberRunner;

public class BaseClass extends BrowserFactory {
	
	
	public TestNGCucumberRunner testNGCucumberRunner;
	
	
	
	@BeforeSuite
	public void beforeSuite() {
		
	}
	
	@BeforeClass
	public void beforeClass() {
		testNGCucumberRunner=new TestNGCucumberRunner(this.getClass());
	}
	
	
	@Parameters({"browserName"})
	@BeforeMethod
	public void beforeMethod(@Optional String browserName) {
		BrowserFactory browserFactory=new BrowserFactory();
		browserFactory.loadBrowser(Objects.requireNonNullElse(browserName,"Chrome"));
	}
	
	
	@AfterClass
	public void afterClass() {
		testNGCucumberRunner.finish();
	}
	
	
	@AfterMethod
	public void afterMethod() {
		loadPageObject().context().browser().close();
		
	}
	
	
	@AfterSuite
	public void endTest() {
		ExtentReportsClass extentReportsClass=new ExtentReportsClass();
		extentReportsClass.getInstance().flush();
	}
	
	
	@DataProvider
	 public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }
        return testNGCucumberRunner.provideScenarios();
    }
	
	
	
	
	
	
	
	
	
	
	
	

}
