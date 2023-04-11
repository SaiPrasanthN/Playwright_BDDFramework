package com.learnings.utilities;

import java.io.File;
import java.nio.file.Paths;
import java.util.Base64;

import com.learnings.base.BaseClass;
import com.microsoft.playwright.Page;

import io.cucumber.plugin.event.TestStepFinished;

public class Utilities extends BaseClass{
	
	
	
	public String takeScreenshot(TestStepFinished finished) {
		Page page=loadPageObject();
		String path=System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"Screenshots"+File.separator+finished.getTestCase().getName();
		byte [] array=page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
	String base64String =Base64.getEncoder().encodeToString(array);
		return base64String;
	}

}
