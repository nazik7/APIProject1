Feature: Validate the Get request for Single User

  Background: Initialize and construct HttpClient
    Given constucted Http client with parameters
      |endpoint|/api/users/2|
      |query   |            |
    And I set custom headers

  Scenario: Validate Get request for Single User returns status code 200
    When the user sends Get request
    Then validates the status code is 200
    And I validate the header is "application/json"
    And I validate response contains attributes:
      |data       |
      |ad         |


