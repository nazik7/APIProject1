Feature: Validate the Get request for List of Users

  Background: Initialize and construct HttpClient
    Given constucted Http client with parameters
      |endpoint|/api/unknown|
      |query   |            |
    And I set custom headers


  Scenario: Validate Get request for List Resources returns status code 200
    When the user sends Get request
    Then validates the status code is 200
    And I validate the header is "application/json"
    And I validate response contains attributes:
      |page       |
      |per_page   |
      |total      |
      |total_pages|
      |data       |
      |ad         |
    And I validate response attributes and values:
      |page       |1 |
      |per_page   |6 |
      |total      |12|
      |total_pages|2 |
