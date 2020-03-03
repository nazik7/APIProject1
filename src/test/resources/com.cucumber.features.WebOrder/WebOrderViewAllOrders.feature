Feature: This is to view all orders
  @order @smoke
  Scenario: Validation of list of all orders
    Given the user navigate to the web orders page
    When the user provide valid username
    And the user provide valid password
    Then the user click view all orders button
    And the user validate list of all orders