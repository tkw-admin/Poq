@DemoTests
Feature: Add an item to bag without an account
  In order to easily easily browse the products
  As a new user
  I want to be asked to register only when I go to checkout

  Scenario: Start app and add item to bag
    Given I have just started the app for the first time
    When I navigate to the shop
    And I select "Clearance" from the menu
    And I select the "5"th item in the list
    And I add it to the bag
    And I select the size
    And I select the bag
    And I select Pay
    Then the Sign in and Register page is displayed