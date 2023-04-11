package com.learnings.listener;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

public class ExtentTestManager {
	
	ExtentReportsClass extentReportsClass=new ExtentReportsClass();
	public Map<Integer,ExtentTest> featuresMap=new HashMap<>();
	public Map<Integer,ExtentTest> scenariosMap=new HashMap<>();
	
	
	
	public ExtentTest getFeature() {
		return featuresMap.get((int)Thread.currentThread().threadId());	
	}
	
	
	
	public ExtentTest getScenario() {
		return scenariosMap.get((int)Thread.currentThread().threadId());	
	}
	
	
	
	public void createScenario(String name) {
		ExtentTest extentTest=getFeature().createNode(Scenario.class,name);
		scenariosMap.put((int)Thread.currentThread().threadId(), extentTest);
	}
	
	public void createFeature(String name) {
		ExtentTest extentTest=extentReportsClass.getInstance().createTest(Feature.class,name);
		featuresMap.put((int)Thread.currentThread().threadId(), extentTest);
	}

}
