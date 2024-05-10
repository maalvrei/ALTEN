@Oscars
Feature: IMDB Search actor
  As a user
  I want to search an Actor was nominated to best actor

@nominatedToBestActor
Scenario Outline: Search Actor and Film
	Given IMDB home page is showed
	When I click menu 
	And select Oscar Awards
	Then I navigate to Oscar <age>
	When best actor section is showed
	Then <actor> and <film> was displayed
	And  verify if he won the oscar <winner>
 Examples:
    | 	film 																										| age		| actor 			| winner |
    | 	Piratas del Caribe: la maldición de la Perla Negra 			| 2004 	| Johnny Deep | false  |
