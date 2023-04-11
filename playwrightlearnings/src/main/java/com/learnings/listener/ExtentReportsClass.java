package com.learnings.listener;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.learnings.base.BaseClass;

public class ExtentReportsClass extends BaseClass {
	
	
	public ExtentSparkReporter extentSparkReporter;
	public static com.aventstack.extentreports.ExtentReports extent;
	
	
	public ExtentReports getInstance() {
		if(extent==null) {
			return createInstance();
		}else {
			return extent;
		}
	}

	
	public ExtentReports createInstance() {
		
		File file=new File(System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"Extent"+File.separator+"ExtentReport.html");
		extentSparkReporter=new ExtentSparkReporter(file);
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setEncoding("utf-8");
		extentSparkReporter.config().setReportName("PlaywrightPratice Report");
		extentSparkReporter.config().setDocumentTitle("PlaywrightPractice Document");
		extentSparkReporter.config().setJs("document.getElementsByClassName('test-content-detail')[0].style.overflow='auto'");
		extent=new ExtentReports();
		extent.attachReporter(extentSparkReporter);
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("BrowserName", loadPageObject().context().browser().browserType().name());
		return extent;
		
	}
	
}
