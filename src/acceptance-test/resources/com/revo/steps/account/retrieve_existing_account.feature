Feature: Operations with account
  Call service to retrieve account data or create new account

  Scenario: Call account data from service
    Given account Id "ACCOUNT_TEST_01"
    When client call account api with id
    Then account balance "1320345.23445" and id "ACCOUNT_TEST_01" retrieved

  Scenario: Create new account
    Given random account id
    And client saves new account information with balance "20.0"
    And client call account api with id
    Then account balance "20.0" retrieved