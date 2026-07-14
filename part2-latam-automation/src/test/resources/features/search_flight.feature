Feature: Search flights

  Scenario: CP-001 - Successful round-trip flight search
    Given the passenger at row 1 from the test data file is on Latam homepage
    When the user selects origin city Cali
    And the user selects destination city Havana
    And the user selects travel dates
    And clicks on search flights button
    Then flights results should be displayed

  Scenario: CP-002 - Origin and destination cannot be the same city
    Given the passenger at row 2 from the test data file is on Latam homepage
    When the user selects origin city Cali
    And the user selects the same city as destination
    And clicks on search flights button
    Then the search should be blocked

  Scenario: CP-003 - Travel dates are mandatory
    Given the passenger at row 3 from the test data file is on Latam homepage
    When the user selects origin city Cali
    And the user selects destination city Havana
    And clicks on search flights button
    Then the search should be blocked