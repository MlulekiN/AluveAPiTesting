Feature: Send Message APi Testing
  Scenario: Validating sending a message using API
    When User sends a message
    Then the server returns a success message