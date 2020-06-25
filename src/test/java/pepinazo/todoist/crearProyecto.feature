Feature: Crear un proyecto en todoist

  Scenario Outline: Add project from plus button
    Given I loged in with <user> and <password>
    When I add a new project form the "+ icon"
    Then The new project is listed in the end

    Examples:
      | user                   | password  |
      | jomarnavarro@gmail.com | Test@1234 |

  Scenario Outline: Add project from "Add project" link
    Given I loged in with <user> and <password>
    When I add a new project form the "Add project link"
    Then The new project is listed in the end

    Examples:
      | user                   | password  |
      | jomarnavarro@gmail.com | Test@1234 |

#  Scenario: Add project from Add project Above option from 3 dots menu

#  Scenario: Add project from Add project Below option from 3 dots menu

#  Scenario: Create project from new task from Inbox

  Scenario: x
    Given I have 5 marbles
    When I give away 3
    Then I have 2 marbles left
