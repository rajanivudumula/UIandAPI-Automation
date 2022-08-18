package test.demo.testrunner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/Features",
		glue={"test.demo.stepdefinitions"},
//		tags= {"@testAPI"},
//		tags= {"@testUI"},
		tags= {"@get"},
//		tags= {"testAPI","@testUI"},
		monochrome=true
		)

public class TestRunner {
	private TestRunner() {
		throw new IllegalStateException("Test Runner utility class");
	}

}
