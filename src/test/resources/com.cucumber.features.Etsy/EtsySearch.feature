Feature: Etsy search

  Background:It will navigate to page before every scenario
    Given the user navigate to the etsy page

  Scenario: Validate spoon search
    When the user provide search item "wooden spoon"
    Then validate title is "Wooden spoon | Etsy"


  Scenario: Validate make up organizer
    When the user provide search item "make up organizer"
    Then validate title is "Make up organizer | Etsy"