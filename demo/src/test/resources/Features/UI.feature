Feature: labcorp mock test for UI Validation

@testUI
Scenario: UI validation
    Given the browser url "https://www.labcorp.com/"
    When click on "Careers" link and switch to Careers tab 
    And search for "QA Test Automation Developer" position
    Then verify searched position found
    Then Verify jobTitle, jobId and jobLocation of the position
    And verify first sentence of third paragraph under Description 
    And verify second bullet point under Management Support
    And verify third requirement
    And verify first suggested automation tool to be familiar with contains "Selenium"
    When click on "Apply Now" and switch to new tab
    Then verify jobTitle
    And click on "Careers Home" to return back to job search
    
