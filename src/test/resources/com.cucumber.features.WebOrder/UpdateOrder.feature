Feature: This is to

  @smoke
  Scenario: Validation of all update
    Given the user navigate to the web orders page
    When the user provide valid username
    And the user provide valid password
    Then the user click view all orders button
    And the user change zip code data to 12345
    Then the user validate the zip code has changed