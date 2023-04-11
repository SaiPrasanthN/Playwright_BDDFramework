package com.learnings.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.learnings.base.BaseClass;
import com.learnings.utilities.Utilities;
import com.microsoft.playwright.Page;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestSourceRead;
import io.cucumber.plugin.event.TestStepFinished;

public class TestListener extends BaseClass implements ConcurrentEventListener {
	
	public ArrayList<String> features=new ArrayList<>();
	public Map<String,Integer> featureMap=new HashMap<>();
	ExtentTestManager extentTestManager=new ExtentTestManager();
	
	public void readFeatures(TestSourceRead testSourceRead) {
		
		String featureSource=testSourceRead.getUri().toString();
		System.out.println(featureSource);
		String featureName=featureSource.split(".*/")[1];
		System.out.println(featureName);
		features.add(featureName);
		
	}
	
	
	
	public void testCaseStarted(TestCaseStarted event) {
		
		Page page=loadPageObject();
		String featureSource=event.getTestCase().getUri().toString();
		String featureName=featureSource.split(".*/")[1];
		Set<String>keySet=featureMap.keySet();
		
		if(!keySet.contains(featureName)) {
			featureMap.put(featureName, 1);
			extentTestManager.createFeature(featureName);
		}else {
			featureMap.put(featureName, featureMap.get(featureName)+1);
		
		
		}
		extentTestManager.createScenario(event.getTestCase().getName());
		extentTestManager.getScenario().assignCategory(event.getTestCase().getTags().toString());
		page.navigate("https://magento.softwaretestingboard.com/?ref=hackernoon.com");
		
	}
	
	public void testStepFinished(TestStepFinished finished) {
		Utilities utilities=new Utilities();
		Result result=finished.getResult();
		
		if (finished.getTestStep() instanceof PickleStepTestStep) {
			
			PickleStepTestStep pickleStepTestStep=(PickleStepTestStep)finished.getTestStep();
			
			
			String key =pickleStepTestStep.getStep().getKeyword();
			String text=pickleStepTestStep.getStep().getText();
			
			if(result.getStatus().toString()=="FAILED") {
				extentTestManager.getScenario().createNode(key + ":" + text).fail(MediaEntityBuilder.createScreenCaptureFromBase64String(utilities.takeScreenshot(finished)).build()).fail(result.getError());
				
			}else if(result.getStatus().toString()=="PASSED") {
				extentTestManager.getScenario().createNode(key + ":" + text).pass("");
			}else {
				extentTestManager.getScenario().createNode(key + ":" + text).skip(key + ":" + text);
			}
			
		}
		
	
	
	
	
	}
	
	
	
	
	

	@Override
	public void setEventPublisher(EventPublisher publisher) {
	publisher.registerHandlerFor(TestSourceRead.class, this::readFeatures);
	publisher.registerHandlerFor(TestCaseStarted.class,this::testCaseStarted);	
	publisher.registerHandlerFor(TestStepFinished.class,this::testStepFinished);
	}

}
