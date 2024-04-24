Feature: Get a user from reqres

  Scenario: Get an user
      Given url "https://reqres.in/api/users/2"
      When method "get"
      Then status 200