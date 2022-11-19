Feature: Validate the Get request for Single User Not Found

  Background: Initialize and construct HttpClient
    Given constucted Http client with parameters
      |endpoint|/api/users/23|
      |query   | |
    And I set custom headers


  Scenario: Validate Get request for Single User Not Found returns status code 404
    When the user sends Get request
    Then validates the status code is 404
    And I validate the header is "application/json"
    And I validate response contains attributes:
    |{}|




