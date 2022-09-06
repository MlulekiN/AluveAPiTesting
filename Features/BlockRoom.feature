Feature: Block Room API Testing

  Scenario Outline: Validating blocking a room using API
    Given The user has signed in
    When The user blocks room
    Then User validates room is blocked from '<check-in>' to '<check-out>'


    Examples:
      | check-in | check-out |
      | 07 Sep   | 08 Sep    |
