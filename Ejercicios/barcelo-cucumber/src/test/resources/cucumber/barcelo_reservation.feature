@SignIn
Feature: Barcelo Reservation
  As a date
  I want to get a five days reservation
  
@Reservation
Scenario: Get a reservation in Barceló Sants Hotel
	Given Barcelo page is showed
	When I select hotel, days and persons
	Then I go to the confirmation page
