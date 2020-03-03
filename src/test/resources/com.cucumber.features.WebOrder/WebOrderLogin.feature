Feature: WebOrders login functionality

  @positive @smoke
   Scenario: WebOrders login positive testing 1
     Given the user navigate to the web orders page
     When the user provide valid username
     And the user provide valid password
     Then the user should see home page

  @menulist
  Scenario: WebOrders login positive testing 2
    Then the user validate order menu list

    @negative @smoke
     Scenario: Web Order Login negative testing
       Given the user navigate to the web orders page
       When the user provide username "Test"
       And the user provide password "admin"
       Then the user validate the text "Invalid Login or Password."
  @negative
  Scenario: Web Order Login negative testing with number
    Given the user navigate to the web orders page
    When the user provide username 1234
    And the user provide password 4567
    Then the user validate the text "Invalid Login or Password."
  @negative
  Scenario: Web Order Login negative testing 2
    Given the user navigate to the web orders page
    When the user provide username "Tester" and password "tt"
    Then the user validate the text "Invalid Login or Password."


