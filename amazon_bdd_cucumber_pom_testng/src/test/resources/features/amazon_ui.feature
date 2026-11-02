@ui
Feature: Amazon UI Automation
  Background:
    Given I open the Amazon home page
  @homepage
  Scenario: Verify home page navigation
    Then I should verify the Amazon home page navigation elements
    And I capture the full page screenshot
  @category
  Scenario: Verify category navigation
    When I navigate to the Mobiles category
    Then I should verify the category page is displayed
    And I capture the category screenshot
  @search
  Scenario: Verify product search
    When I search for product "Samsung"
    Then I should verify the search results for "Samsung"
    And I capture the search results screenshot
