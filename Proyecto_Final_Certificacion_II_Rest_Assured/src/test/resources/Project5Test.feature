Feature: Employees endpoint
  Background: Employees endpoints should allow to get, create, update and delete employees

  @delete
  Scenario: /delete should delete a employee
    Given I perform a DELETE to delete the id "24"
    Then I verify status code 200 is returned
    And I verify that the employee with id "24" no longer exists
    #Given I want to get the employee of id: "24" #Como se trata de un fake server, hacer esto seria innecesario y siempre fallaria, se evita
    #Then I verify status code 404 is returned

  @getByID
  Scenario Outline: /employee/{id} should return a specific employee
    Given I perform a GET to get the employee of id: "<id>"
    Then I verify status code 200 is returned
    And I verify that the body does not have size 0
    And I verify I get the employee of id <id>
    Examples:
      | id |
      | 21 |
      | 22 |
      | 23 |

  @put
  Scenario: /update/{id} should update a specific employee's information
    Given I perform a PUT to the update a employee of id "21" with the following data:
      | Santiago | 7050 | 19 |
    Then I verify status code 200 is returned
    And I verify that the body does not have size 0
    And I verify the following data in the body response
      | Santiago | 7050 | 19 |

  @getByIDNotFound
  Scenario: /employee/{id} shouldn't return a employee with non existent id
    Given I perform a GET to get the employee of id: "56"
    Then I verify status code 404 is returned
    And I verify that the body has size 0

  @badRequestPutForEmptyFieldAge
  Scenario: /update/{id} with payload with empty fields should return error
    Given I perform a PUT to the update a employee of id "21" with the following data:
      | Santiago | 7050 |  |
    Then I verify status code 400 is returned
    And I verify that the body has size 0

  @badRequestPutForEmptyFieldSalary
  Scenario: /update/{id} with payload with empty fields should return error
    Given I perform a PUT to the update a employee of id "21" with the following data:
      | Santiago |  | 21 |
    Then I verify status code 400 is returned
    And I verify that the body has size 0

  @badRequestPutForEmptyFieldName
  Scenario: /update/{id} with payload with empty fields should return error
    Given I perform a PUT to the update a employee of id "21" with the following data:
      |  | 4020 | 21 |
    Then I verify status code 400 is returned
    And I verify that the body has size 0

  @badRequestPutForWrongFormatSalary
  Scenario: /update/{id} with payload with empty fields should return error
    Given I perform a PUT to the update a employee of id "21" with the following data:
      | Santiago | WrongFormat | 19 |
    Then I verify status code 400 is returned
    And I verify that the body has size 0

  @badRequestPutForWrongFormatAge
  Scenario: /update/{id} with payload with empty fields should return error
    Given I perform a PUT to the update a employee of id "21" with the following data:
      | Santiago | 4020 | WrongFormat |
    Then I verify status code 400 is returned
    And I verify that the body has size 0
