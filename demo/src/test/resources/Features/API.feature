Feature: labcorp mock test for API Validation

@testAPI @post
Scenario: API validation of POST call
    Given The endpoint 
    When request sent
    And Post call made
    Then verify post call successfull 
   
@testAPI @get
Scenario: API validation of GET call
    Given The endpoint 
    When Get call made
    Then verify successfully posted 
    
