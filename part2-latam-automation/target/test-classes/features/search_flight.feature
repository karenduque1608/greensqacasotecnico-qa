Feature: Search flights

  Scenario: Search a flight from Cali to Havana

    Given the user is on Latam homepage
    When the user selects origin city Cali
    And the user selects destination city Havana
    And the user selects travel dates
    And clicks on search flights button
    Then flights results should be displayed