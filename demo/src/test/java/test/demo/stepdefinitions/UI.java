package test.demo.stepdefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
//import net.thucydides.core.annotations.DefaultUrl;
import test.demo.pageobjects.UIPageObject;

public class UI {
	
	private final static Logger logger = LoggerFactory.getLogger(UI.class);
	
	UIPageObject po;

	@Given("^the browser url \"([^\"]*)\"$")
	public void browserURL(String url) {
		System.out.println("url " + url);
//		po.open(); this uses @DefaultUrl
		po.openApplication(url);
	}

	@When("^click on \"([^\"]*)\" link and switch to Careers tab$")
	public void clickOnLink(String text) {
		po.clickLink(text);
		po.SwithToCareersTab();
	}

	@When("^search for \"([^\"]*)\" position$")
	public void search_for_position(String text) {
		po.searchForSpecificJob(text);
	}

	@Then("^verify searched position found$")
	public void search_position_found() {
		po.searchResultFound();
	}

	@SuppressWarnings("deprecation")
	@And("^Verify jobTitle, jobId and jobLocation of the position$")
	public void verify_jobTitle_jobId_and_jobLocation() {
		String actText =null;
		actText=po.verifyJobTitle();
		Assert.assertEquals(actText, "QA Test Automation Developer");

		actText= po.verifyJobId();
		Assert.assertEquals(actText, "Job Id : 21-90223_RM");
		
		actText= po.verifyJobLocation();
		Assert.assertEquals(actText, "Location"+"\nDurham, North Carolina, United States of America");
	}
	
	@Then("^verify first sentence of third paragraph under Description$")
	public void verify_first_sentence_of_third_paragraph_under_Description() {
		String expText="The right candidate for this role will participate in the test automation technology development and best practice models.";
	    po.verifyMatchingParagraph(expText);
	}


	@Then("^verify second bullet point under Management Support$")
	public void verify_second_bullet_point_under_Management_Support() {
	   String expText="Prepare test plans, budgets, and schedules.";
	   po.verifyExactParagraph(expText);
	}

	@Then("^verify third requirement$")
	public void verify_third_requirement() {
	  String expText="5+ years of experience in QA automation development and scripting.";
	  po.verifyExactParagraph(expText);
	}

	@Then("^verify first suggested automation tool to be familiar with contains \"([^\"]*)\"$")
	public void verify_first_suggested_automation_tool_to_be_familiar_with_contains(String text) {
	  String expText=text;
	  System.out.println("textverified "+text);
	  po.verifyMatchingParagraph(expText);
	}
	
	@When("^click on \"([^\"]*)\" and switch to new tab$")
	public void click_on(String text) {
	  po.clickButton(text);
	  po.switchNewTab();
	}

	@Then("^verify jobTitle$")
	public void verify_jobTtile() {
		po.enterLoginDetails();
		String actText =null;
		actText=po.verifyJobTitle();
		Assert.assertEquals(actText, "QA Test Automation Developer");
	}
	
	@Then("^click on \"([^\"]*)\" to return back to job search$")
	public void click_on_to_return_back_to_job_search(String arg1) {
         po.searachResult();
	}

}
