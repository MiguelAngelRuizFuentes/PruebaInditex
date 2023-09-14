Feature: Pet Store Automation

  Background:
    * url 'https://petstore.swagger.io/v2'

  Scenario: Create a user and retrieve user data
    Given path '/user'
    And request { "username": "testUser", "firstName": "John", "lastName": "Doe", "email": "test@example.com" }
    When method post
    Then status 200
    And match response == { id: '#number', username: 'testUser', firstName: 'John', lastName: 'Doe' }
    * def userId = response.id

  Scenario: Get pets sold by calling /pet/findByStatus
    Given path '/pet/findByStatus'
    And param status = "sold"
    When method get
    Then status 200
    * def pets = response
    * def soldPets = karate.filter(pets, function(pet){ return pet.status == 'sold' })
    * def petNames = karate.map(soldPets, function(pet){ return { id: pet.id, name: pet.name } })
    * print 'Sold Pets:', petNames

  Scenario: Count pets with the same name
    * def PetCount = function(pets) {
      var countMap = {};
      for (var pet of pets) {
        if (countMap[pet.name]) {
          countMap[pet.name]++;
        } else {
          countMap[pet.name] = 1;
        }
      }
      return countMap;
    }

    * def pets = [
      { name: 'William', id: 1 },
      { name: 'William', id: 2 },
      { name: 'Floyd', id: 3 },
      { name: 'William', id: 4 }
    ]
    * def count = PetCount(pets)
    * print 'Pet Name Count:', count
