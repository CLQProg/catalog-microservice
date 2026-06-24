Feature: IT of prices REST Endpoint

  Background:
    * url 'http://localhost:8080'
    * eval db.update(read('classpath:prices/sql/setup.sql'))

  Scenario: 1 GET to /api/ecommerce/v1/brands/1/products/35455/price at 10:00 on 2020-06-14T10:00:00Z
    Given path 'api', 'ecommerce', 'v1', 'brands', 1, 'products', 35455, 'price'
    And params { date: '2020-06-14T10:00:00Z' }
    When method get
    Then status 200
    And match response == read('classpath:prices/api/get-scenario-1.json')

  Scenario: 2 GET to /api/ecommerce/v1/brands/1/products/35455/price on 2020-06-14T16:00:00Z
    Given path 'api', 'ecommerce', 'v1', 'brands', 1, 'products', 35455, 'price'
    And params { date: '2020-06-14T16:00:00Z' }
    When method get
    Then status 200
    And match response == read('classpath:prices/api/get-scenario-2.json')

  Scenario: 3 GET to /api/ecommerce/v1/brands/1/products/35455/price on 2020-06-14T21:00:00Z
    Given path 'api', 'ecommerce', 'v1', 'brands', 1, 'products', 35455, 'price'
    And params { date: '2020-06-14T21:00:00Z' }
    When method get
    Then status 200
    And match response == read('classpath:prices/api/get-scenario-3.json')

  Scenario: 4 GET to /api/ecommerce/v1/brands/1/products/35455/price on 2020-06-15T10:00:00Z
    Given path 'api', 'ecommerce', 'v1', 'brands', 1, 'products', 35455, 'price'
    And params { date: '2020-06-15T10:00:00Z' }
    When method get
    Then status 200
    And match response == read('classpath:prices/api/get-scenario-4.json')

  Scenario: 5 GET to /api/ecommerce/v1/brands/1/products/35455/price on 2020-06-16T21:00:00Z
    Given path 'api', 'ecommerce', 'v1', 'brands', 1, 'products', 35455, 'price'
    And params { date: '2020-06-16T21:00:00Z' }
    When method get
    Then status 200
    And match response == read('classpath:prices/api/get-scenario-5.json')

  Scenario: 6 GET to /api/ecommerce/v1/brands/1/products/45455/price on 2020-06-16T21:00:00Z
    Given path 'api', 'ecommerce', 'v1', 'brands', 1, 'products', 45455, 'price'
    And params { date: '2020-06-16T21:00:00Z' }
    When method get
    Then status 404

  Scenario: 7 GET to /api/ecommerce/v1/brands/1/products/45455/price on 2020-06-32T25:00:00Z
    Given path 'api', 'ecommerce', 'v1', 'brands', 1, 'products', 45455, 'price'
    And params { date: '2020-06-32T25:00:00Z' }
    When method get
    Then status 400
