@SignIn
Feature: Barcelo Reservation
  As a date
  I want to get a five days reservation
  
@Reservation
Scenario: Get a reservation in Barceló Sants Hotel
	Given Barcelo page is showed
	When I select the hotel
	And I select the days
	And I select the number of persons
	Then I go to the confirmation page
