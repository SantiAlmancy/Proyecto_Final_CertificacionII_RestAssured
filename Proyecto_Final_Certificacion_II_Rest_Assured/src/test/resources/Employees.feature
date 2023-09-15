Feature: Employees endpoint
  Background: Employees endpoints should allow to get, create, update and delete employees

    @getAll
    Scenario: /employees should return all the employees
      Given I perform a GET to the employees endpoint
      Then I verify status code 200 is returned
      And I verify that the body does not have size 0

    @post
    Scenario: /create should create an employee
      Given I perform a POST to the create endpoint with the following data
        | Diego | 3500 | 26 |
        Then I verify status code 200 is returned
      And I verify that the body does not have size 0
      And I verify the following data in the body response
        | Diego | 3500 | 26 |