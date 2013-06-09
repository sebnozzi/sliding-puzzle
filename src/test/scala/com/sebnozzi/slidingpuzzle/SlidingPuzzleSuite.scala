package com.sebnozzi.slidingpuzzle

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class SlidingpuzzleSuite extends FunSuite with BeforeAndAfter {

  var game: SlidingPuzzleGame = _

  before {
    game = new SlidingPuzzleGame(horizontalTiles = 4, verticalTiles = 3)
  }

  test("a game has tiles") {
    assert(game.tiles.size === 12)
  }

}
