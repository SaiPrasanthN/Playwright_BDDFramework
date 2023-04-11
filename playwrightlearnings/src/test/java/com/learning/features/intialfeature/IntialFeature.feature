
@Test
Feature: Feature 1
  I want to use this template for my feature file

  @Scenario1
  Scenario Outline: Scenario 1
    Given I am on login Page
    And login with <userName> and <password>
    And verifies if user is on homePage

Examples:
|userName|password|
|Prashanth| Prashanth@12|
 
