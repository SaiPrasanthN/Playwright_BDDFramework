package com.learnings.runner;

import org.testng.annotations.Test;

import com.learnings.base.BaseClass;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;


	
	@CucumberOptions(features = "src//test//java//com//learning//features",glue = {"com.learnings.stepdefinitions"},tags = "@Test", plugin = {"com.learnings.listener.TestListener"})
	public class RunnerClass extends BaseClass {
	@Test(description = "Tests", dataProvider = "scenarios")
	public void runScenarios(PickleWrapper wrapper, FeatureWrapper featureWrapper) {
		testNGCucumberRunner.runScenario(wrapper.getPickle());
	}
	

}
