Feature: Sauce Demo Login and Product

  Scenario: Login to Sauce Demo with valid credentials
    Given I am on the Sauce Demo login page
    When I enter "standard_user" as my username
    And I enter "secret_sauce" as my password
    And I click on the Login button
    Then I should be logged in to the Sauce Demo website

  Scenario: Login to Sauce Demo with invalid credentials
    Given I am on the Sauce Demo login page
    When I enter "invalid_username" as my username
    And I enter "invalid_password" as my password
    And I click on the Login button
    Then I should see an error message

  Scenario: Add a product to the cart
    Given I am logged in to the Sauce Demo website
    When I click on the Add to Cart button for the first product
    Then I should successfully add the product to the cart

  Scenario: Remove a product from the cart
    Given I have a product in my cart
    When I click on the Remove button for the product
    Then I should successfully remove from my cart