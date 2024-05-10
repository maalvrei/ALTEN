@SearchActorInFilm
Feature: IMDB Search Film 
  As a user
  I want to Verify that a movie appears in an actor's filmography 

@SearchActorInFilm 
Scenario Outline: Verify that a movie appears in an actor's filmography  
	Given IMDB home page is showed
	When I Search the actor <actor> 
	Then the results page is show
	When I navigate to filmography
	When I search <film> , <age>
 Examples:
    | 	actor 							| age		| film 									| 
    | 	Leonardo DiCaprio | 2013 	| El lobo de Wall Street 	|
