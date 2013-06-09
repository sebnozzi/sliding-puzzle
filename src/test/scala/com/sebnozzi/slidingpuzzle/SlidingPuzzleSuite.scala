package com.sebnozzi.slidingpuzzle

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class SlidingpuzzleSuite extends FunSuite with BeforeAndAfter {

  var game: SlidingPuzzleGame = _

  before {
    game = new SlidingPuzzleGame(columns = 4, rows = 3)
  }

  test("a game with 12 tiles") {
    game = new SlidingPuzzleGame(columns = 4, rows = 3)
    assert(game.tiles.size === 12)
  }

  test("a game with 9 tiles") {
    game = new SlidingPuzzleGame(columns = 3, rows = 3)
    assert(game.tiles.size === 9)
  }
  
  test("initially, game is in solved state"){
    assert(game.isSolved)
  }
  
  
}
