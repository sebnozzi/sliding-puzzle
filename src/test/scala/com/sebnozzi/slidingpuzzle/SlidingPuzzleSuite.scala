package com.sebnozzi.slidingpuzzle

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class SlidingpuzzleSuite extends FunSuite with BeforeAndAfter {

  var game: SlidingPuzzleGame = _

  before {
    game = new SlidingPuzzleGame(columns = 4, rows = 3)
  }

  test("a game of dimensions 4x3 should have 12 tiles") {
    val game = new SlidingPuzzleGame(columns = 4, rows = 3)
    assert(game.tiles.size === 12)
  }

  test("a Position consists of column and row") {
    val pos1 = Position(col = 1, row = 1)
    val pos2 = Position(col = 2, row = 3)
    assert(pos1.col === 1)
    assert(pos1.row === 1)
    assert(pos2.col === 2)
    assert(pos2.row === 3)
  }

  test("a game of dimensions 3x2 should have 9 tiles") {
    val game = new SlidingPuzzleGame(columns = 3, rows = 3)
    assert(game.tiles.size === 9)
  }

  test("a tile has an original position") {
    assert(game.tiles.head.initialPosition === (1, 1))
    assert(game.tiles.last.initialPosition === (4, 3))
  }

  test("initially, game is in solved state") {
    assert(game.isSolved)
  }

  test("it is possible to define a hidden tile") {
    val tile = game.tileAt(4, 3)
    game.setHiddenTileAt(4, 3)
    assert(game.hiddenTile === tile)
  }

  test("defining the first tile as hidden") {
    val tile = game.tileAt(1, 1)
    game.setHiddenTileAt(1, 1)
    assert(game.hiddenTile === tile)
  }

  ignore("it is possible to get all tile positions") {
    fail("pending")
  }

  // move tile to hidden slot
  // re-show hidden tile
  // detect solved state after some moves
  // shuffle tiles
  // revert to initial state
  // count number of moves
  // make sure number of moves is reset

}
