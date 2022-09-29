Feature: Block Room API Testing

  Background: The user logs in the website
    Given The user has signed in

  Scenario Outline: Validating blocking a room using API

    When The user blocks room
    Then User validates room is blocked from '<check-in>' to '<check-out>'

    When User deletes a blocked room
    Then User validates room is deleted





    Examples:
      | check-in | check-out |
      | 07 Oct   | 08 Oct    |
