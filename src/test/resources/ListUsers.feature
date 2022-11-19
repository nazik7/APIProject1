Feature: Validate the Get request for List of Users

  Background: Initialize and construct HttpClient
    Given constucted Http client with parameters
    |endpoint|/api/users|
    |query   |page=2    |
    And I set custom headers


  Scenario: Validate Get request for List of Users returns status code 200
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
      |page       |2 |
      |per_page   |6 |
      |total      |12|
      |total_pages|2 |

    And I validate the data of the first user
    |id|7|
    |email|michael.lawson@reqres.in|
    |first_name|Michael            |
    |last_name |Lawson             |
    |avatar    |https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg|



