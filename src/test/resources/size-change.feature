
Feature: Changing game size

  The user might want to change game size, when this happens a new
  game should be presented in solved state.
  
  It's up to the user to shuffle and start playing.
  
  Background:
    Given I am presented with a game in size "3x3"
  
  Scenario: Initial moves-count
    Then the moves counter should be at 0

  Scenario: Changing size to "4x3"
    When I change the size to "4x3"
    Then I should see a game with 12 tiles
    And the game should be in solved state
    And the moves counter should be at 0
