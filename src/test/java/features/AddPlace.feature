Feature: Place API

@AddPlace
Scenario Outline: Verify new place is added successfully into Google Maps
Given User enter Add place payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with post http request
Then Verify status code is 200

Examples:
	|name|language|address|
	|Mohan|Telugu|Kuppam|
	|Ramcharan|Tamil|Chennai|
	|Puneeth|Kannada|Bangalore|

@DeletePlace
Scenario: Verify if delete place API is working
Given User enter Delete place payload 
When User calls "DeletePlaceAPI" with post http request
Then Verify status code is 200

