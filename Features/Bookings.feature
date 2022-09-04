Feature: Booking API Testing

  Scenario Outline: Validating book now functionality with API
    When The user has selects '<check-in>' and '<check-out>' date
    Then the server returns availability

    When The selects book now
    Then The server returns confirmation

    Examples:
      | check-in   | check-out  |
      | 2022-09-06 | 2022-09-08 |