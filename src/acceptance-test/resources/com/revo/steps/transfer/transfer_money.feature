Feature: Transfer money from one account to another
  Application transfers money to account

  Scenario: Transfer money from one account to another
    Given two accounts with balance "20.0" and "30.0"
    When client transfers from first to second "10.0"
    Then first account contains "10.0" and second "40.0"

  Scenario: Transfer negative amount of money from one account to another
    Given two accounts with balance "20.0" and "30.0"
    When client transfers from first to second "-10.0"
    Then receives error message "ERROR_NEGATIVE_AMOUNT"

  Scenario: Transfer zero amount of money from one account to another
    Given two accounts with balance "20.0" and "30.0"
    When client transfers from first to second "0"
    Then receives error message "ERROR_ZERO_AMOUNT"

  Scenario: Client tries to transfer more than he can
    Given two accounts with balance "20.0" and "30.0"
    When client transfers from first to second "40.0"
    Then receives error message "ERROR_INSUFFICIENT_FUNDS"