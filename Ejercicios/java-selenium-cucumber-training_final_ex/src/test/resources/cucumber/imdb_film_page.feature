@FilmPageDetails
Feature: IMDB Search film
  As a user
  I want to verify number of photos to Film Page and PhotoGallery are equals.

@PhotoGalleryNumberImages
Scenario Outline: Search Actor and Film
	Given IMDB home page is showed
	When I click Searcn and type <film> <age>
	And click on film
	Then Film page is showed
	When I navigate to Photo Gallery clicking on Images(number) 
	Then Verify to number of photos are equals to number
 Examples:
    | 	film 									| age		|
    | 	Django Unchained 			| 2012 	|
