@api
Feature: Petstore API CRUD
  Scenario: Create read update and delete a pet
    Given I prepare unique pet test data
    When I create a pet using POST request
    Then I should verify the pet creation response
    When I retrieve the pet using GET request
    Then I should verify the retrieved pet details
    When I update the pet using PUT request
    Then I should verify the updated pet details
    When I delete the pet using DELETE request
    Then I should verify the pet is no longer available
