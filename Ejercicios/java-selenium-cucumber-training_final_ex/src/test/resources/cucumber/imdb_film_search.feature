@SearchFilm
Feature: IMDB Search Actor into Film
  As a user
  I want to search an Actor & Film

@SearchFilmAndActorOnFullCasting
Scenario Outline: Login Incorrect - user not exist
	Given IMDB home page is showed
	When I Search the film <film> and <age>
	Then film page is showed
	When I navigate to All casting 
	Then All casting section is showed
	Then <actor> is found into actor's List
 Examples:
    | 	film 						| age		| actor 			| 
    | 	Espartacus 			| 1960 	| Hume Cronyn |
