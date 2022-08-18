package test.demo.pageobjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.labcorp.com/")
public class UIPageObject extends PageObject {

	private final static Logger logger = LoggerFactory.getLogger(UIPageObject.class);

	String careerurl = null;
	String currentHandle = null;
	String ApplyNowbeforeTab=null;
	String Searchvalue = null;
	WebElement searchInputField = null;

	public UIPageObject(WebDriver driver) {
		super(driver);
	}

	public void openApplication(String url) {
		openUrl(url);
	}

	public void clickLink(String text) {
		WebElementFacade xpath = find(
				By.xpath("//*[@id='login-container']/ul/li[2]/a[contains(text(),'" + text + "')]"));
		careerurl = xpath.getAttribute("href");
		System.out.println("career url ::" + careerurl);
//		logger.info("career url {}" ,careerurl);

		currentHandle = getDriver().getWindowHandle();
		xpath.waitUntilClickable();
		xpath.click();

	}

	public void SwithToCareersTab() {
		Set<String> handles = getDriver().getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(currentHandle)) {
				// Switch to the opened tab
				getDriver().switchTo().window(actual);
				// opening the URL saved.
//				getDriver().get(careerurl);
			}
		}

	}

	public void searchForSpecificJob(String text) {
		Searchvalue = text;

		List<WebElement> searchInputFields = getDriver().findElements(By.xpath("//*[@id='typehead']"));
		int size = searchInputFields.size();
		System.out.println("total search input fields ::" + size);
		logger.info("total search input fields {}", size);

		if (size >= 2) {
			searchInputField = searchInputFields.get(1);
		}
		searchInputField.sendKeys(text);
	}

	public void searchResultFound() {
		searchInputField.sendKeys(Keys.RETURN);

		WebElementFacade searchresult = find(
				By.xpath("//ul/li[1][@class='jobs-list-item']/div/span/a/div/span[normalize-space(text()='"
						+ Searchvalue + "')]"));
		searchresult.waitUntilPresent();
		searchresult.click();
	}

	public String verifyJobTitle() {
		WebElementFacade jobTitle = find(By.xpath("//h1[contains(text(),'" + Searchvalue + "')]"));
		jobTitle.waitUntilPresent();
		Serenity.recordReportData().withTitle("jobTitle").andContents(jobTitle.getText());
		return jobTitle.getText();
	}

	public String verifyJobId() {
		WebElementFacade jobId = find(By.xpath("//div[@class='job-other-info']/span[3]/span"));
		jobId.waitUntilPresent();
		Serenity.recordReportData().withTitle("jobId").andContents(jobId.getText());
		return jobId.getText();
	}

	public String verifyJobLocation() {
		WebElementFacade jobLocation = find(By.xpath("//section/div/div[1]/span[2]/span"));
		jobLocation.waitUntilPresent();
		System.out.println("location ::"+jobLocation.getText());
		Serenity.recordReportData().withTitle("jobLocation").andContents(jobLocation.getText());
		return jobLocation.getText();		
	}

	public void clickButton(String text) {
		WebElementFacade button = find(
				By.xpath("//a[@ph-tevent='apply_click'][normalize-space(text()='"+ text + "')]"));
		
		ApplyNowbeforeTab=getDriver().getWindowHandle();
		button.waitUntilPresent();
		button.click();
		
	}

	public void switchNewTab() {
		String ApplyNowAfterTab="https://labcorp.wd1.myworkdayjobs.com/External/job/Durham-NC-8-Moore-Dr/QA-Test-Automation-Developer_21-90223_RM/apply";

		Set<String> handles = getDriver().getWindowHandles();
		for (String actual : handles) {
			if (!actual.equalsIgnoreCase(ApplyNowbeforeTab)) {
				// Switch to the opened tab
				getDriver().switchTo().window(actual); // switches to new tab
				// opening the URL saved.
				getDriver().get(ApplyNowAfterTab);
			}
		}
		waitABit(3000);
		
				
	}

	public void enterLoginDetails() {
		WebElementFacade email = find(
				By.xpath("//input[@id='input-4']"));
		email.sendKeys("rajani.vudumula@gmail.com");
		
		WebElementFacade pwd = find(
				By.xpath("//input[@id='input-5']"));
		pwd.sendKeys("Hara1243!");
		
		WebElement submit = getDriver().findElement(By.xpath("//div/button[@type='submit']"));
//		submit.isSelected();
//		JavascriptExecutor js=(JavascriptExecutor)getDriver();
//		js.executeScript("arguments[0].click()", submit);   //err: not able to click on submit button
		
		Actions action=new Actions(getDriver());
		action.moveToElement(submit).click().build().perform();
	}

	public void searachResult() {
		WebElementFacade link = find(By.xpath("//button[contains(text(),'Careers Home')]"));
		link.waitUntilClickable();
		link.click();
	}

	public void verifyMatchingParagraph(String expText) {
		WebElementFacade para = find(By.xpath("//p[contains(text(),'"+expText+"')]"));
		para.waitUntilPresent();
		para.isDisplayed();
		Serenity.recordReportData().withTitle("actual Paragraph").andContents(para.getText());
	}

	public void verifyExactParagraph(String expText) {
		WebElementFacade para = find(By.xpath("//p[text()='"+expText+"']"));
		para.waitUntilPresent();
		para.isDisplayed();
		Serenity.recordReportData().withTitle("actual Paragraph").andContents(para.getText());
	}
	
}
