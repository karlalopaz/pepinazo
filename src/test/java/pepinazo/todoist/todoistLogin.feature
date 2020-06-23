Feature: Todoist Login

  Scenario: User is able to login with email credentials
    Given I navigate to todoist site
    When I enter my credentials
    Then I see the project page

  Scenario: User is able to login with email credentials from table
    Given  I navigate to todoist site
    When I enter <user> and <password>
    Then I see the project page

    Examples:
      | user                   | password  |
      | jomarnavarro@gmail.com | Test@1234 |
      | jomarnavarro@gmail.com | Test@1234 |
      | jomarnavarro@gmail.com | Test@1235 |

