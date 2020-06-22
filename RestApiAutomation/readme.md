# REST API Test Automation

Automation to test the following APIs in Java

*  Create a new deck of cards
	- GET https://deckofcardsapi.com/api/deck/new/
	- Support adding Jokers with a POST

*  Draw one or more cards from a deck
	- GET https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/

## Prerequisites
* Install java minimum version 8
* Install Maven 3.6.1


### Running the tests

1. Clone or download the project as zip 
2. Execute mvn test from the directory containing pom.xml
3. The test report can be found in target/surefire-reports/index.html
 



