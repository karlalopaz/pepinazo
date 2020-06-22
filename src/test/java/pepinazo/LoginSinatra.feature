Feature: Sinatra Login

  Scenario: Login Happy path

    Given I have the correct credentials
    When I navigate to sinatra site
    And I enter the correct credentials
    Then I will be in the Songs Page
    And I will see a  welcome message

    Scenario: Login wrong password

      Given I have the wrong password for a valid user
      When I navigate to sinatra site
      And I enter the valid user and wrong password
      Then I will get an error message
      And I will be in the login page

      Scenario: Login no user

        Given I have no user
        When I navigate to sinatra site
        And I try to login with no user data
        Then I will get an error message
        And I will be in the login page

        Scenario: Login no password

          Given I have no password
          When I navigate to sinatra site
          And I try to login with no password data
          Then I will get an error message
          And I will be in the login page


