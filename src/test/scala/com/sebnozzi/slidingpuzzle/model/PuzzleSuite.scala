package com.sebnozzi.slidingpuzzle.model

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import PositionConversions._
import org.scalatest.Finders

class puzzleSuite extends FunSuite with BeforeAndAfter {

  var puzzle: Puzzle = _

  before {
    puzzle = new Puzzle(columns = 4, rows = 3)
  }

  test("a puzzle of dimensions 4x3 should have 12 tiles") {
    val puzzle = new Puzzle(columns = 4, rows = 3)
    assert(puzzle.tiles.size === 12)
  }

  test("a puzzle of dimensions 3x3 should have 9 tiles") {
    val puzzle = new Puzzle(columns = 3, rows = 3)
    assert(puzzle.tiles.size === 9)
  }

  test("a tile has an original position") {
    assert(puzzle.tiles.head.initialPosition === Position(1, 1))
    assert(puzzle.tiles.last.initialPosition === Position(4, 3))
  }

  test("a tile's current position is initially the initial position") {
    val tile = puzzle.tiles.head
    assert(tile.currentPosition === tile.initialPosition)
  }

  test("changing a tile's current position") {
    val tile = puzzle.tiles.head
    tile.currentPosition = (2, 2)
    assert(tile.currentPosition != tile.initialPosition)
  }

  test("swapping positions between two tiles") {
    val tile1 = puzzle.tiles(0)
    val tile2 = puzzle.tiles(1)
    tile1.swapPositionWith(tile2)
    assert(tile1.currentPosition === tile2.initialPosition)
    assert(tile2.currentPosition === tile1.initialPosition)
  }

  test("a tile knows its puzzle") {
    puzzle.tiles.foreach { tile => assert(tile.puzzle === puzzle) }
  }

  test("a puzzle can return a rect for valid positions") {
    val puzzle4x3 = new Puzzle(columns = 4, rows = 3)
    val puzzle2x2 = new Puzzle(columns = 2, rows = 2)
    assert(puzzle4x3.positionsRect === Rect((1, 1), (4, 3)))
    assert(puzzle2x2.positionsRect === Rect((1, 1), (2, 2)))
  }

  test("a tile knows its adjacent tiles") {
    val puzzle = new Puzzle(columns = 2, rows = 2)
    val topLeft = puzzle.tileAt(1, 1)
    val topRight = puzzle.tileAt(2, 1)
    val bottomLeft = puzzle.tileAt(1, 2)
    val bottomRight = puzzle.tileAt(2, 2)
    assert(topLeft.adjacentTiles === List(topRight, bottomLeft))
    assert(topRight.adjacentTiles === List(topLeft, bottomRight))
  }

  test("a tile knows directional adjacent tiles") {
    val puzzle = new Puzzle(columns = 3, rows = 3)
    val tile = puzzle.tileAt(1, 2)
    assert(tile.tileAbove.get.initialPosition === Position(1, 1))
    assert(tile.tileRight.get.initialPosition === Position(2, 2))
    assert(tile.tileBelow.get.initialPosition === Position(1, 3))
    assert(tile.tileLeft === None)
  }

  test("asking if one tile is adjacent to another") {
    val puzzle = new Puzzle(columns = 3, rows = 3)
    assert(puzzle.tileAt(1, 1).isAdjacentTo(puzzle.tileAt(2, 1)))
    assert(!puzzle.tileAt(1, 1).isAdjacentTo(puzzle.tileAt(3, 2)))
  }

  test("it is possible to define a hidden tile") {
    val tile = puzzle.tileAt(4, 3)
    puzzle.setHiddenTileAt(4, 3)
    assert(puzzle.hiddenTile === tile)
  }

  test("moving to empty slot") {
    val tile1 = puzzle.tileAt(4, 2)
    val tile2 = puzzle.tileAt(4, 3)
    tile2.makeHidden()
    tile1.moveToEmptySlot()
    assert(tile1.currentPosition === Position(4, 3))
    assert(tile2.currentPosition === Position(4, 2))
    assert(puzzle.tileAt(4, 2) === tile2)
    assert(puzzle.tileAt(4, 3) === tile1)
  }

  test("moving to empty slot when not adjacent to it should do nothing") {
    val tile1 = puzzle.tileAt(1, 1)
    val tile2 = puzzle.tileAt(4, 3)
    puzzle.setHiddenTileAt(4, 3)
    val result = tile1.moveToEmptySlot()
    assert(result === false)
    assert(tile1.currentPosition === Position(1, 1))
    assert(tile2.currentPosition === Position(4, 3))
  }

  test("asking if tile can be moved to empty slot") {
    val tile1 = puzzle.tileAt(1, 1)
    val tile2 = puzzle.tileAt(4, 3)
    puzzle.setHiddenTileAt(4, 3)
    assert(tile1.canMoveToEmptySlot === false)
  }

  test("asking if a puzzle has a hidden tile") {
    assert(puzzle.hasHiddenTile === false)
    puzzle.setHiddenTileAt(4, 3)
    assert(puzzle.hasHiddenTile === true)
  }

  test("moving to empty slot, when no tile is hidden, should be false") {
    val tile1 = puzzle.tileAt(3, 3)
    val tile2 = puzzle.tileAt(4, 3)
    assert(tile1.canMoveToEmptySlot === false)
  }

  test("making a tile hidden") {
    val tile1 = puzzle.tileAt(1, 1)
    tile1.makeHidden
    assert(puzzle.hiddenTile === tile1)
  }

  test("unhiding the tile") {
    assert(puzzle.hasHiddenTile === false)
    val tile = puzzle.tileAt(1, 1)
    tile.makeHidden
    assert(puzzle.hasHiddenTile)
    tile.makeVisible
    assert(puzzle.hasHiddenTile === false)
  }

  test("initially, puzzle is in a solved state") {
    assert(puzzle.isSolved)
  }

  test("ask tile if in initial position") {
    assert(puzzle.tileAt(1, 1).isAtInitialPosition)
    puzzle.tileAt(2, 2).swapPositionWith(puzzle.tileAt(3, 2))
    assert(puzzle.tileAt(2, 2).isAtInitialPosition === false)
  }

  test("as soon as one move is made, the puzzle is not in solved state") {
    val tile1 = puzzle.tileAt(4, 2)
    val tile2 = puzzle.tileAt(4, 3)
    tile2.makeHidden
    tile1.moveToEmptySlot()
    assert(puzzle.isSolved === false)
  }

  test("puzzle solved after putting back the tile") {
    val tile = puzzle.tileAt(4, 2)
    val hiddenTile = puzzle.tileAt(4, 3)
    hiddenTile.makeHidden
    val firstMoveDone = tile.moveToEmptySlot()
    val secondMoveDone = tile.moveToEmptySlot()
    assert(firstMoveDone)
    assert(secondMoveDone)
    assert(puzzle.isSolved)
  }

  test("asking for a random adjacent tile") {
    val tile = puzzle.tileAt(2, 2)
    val possibleTiles = List(
      puzzle.tileAt(3, 2),
      puzzle.tileAt(1, 2),
      puzzle.tileAt(2, 1),
      puzzle.tileAt(2, 3))
    (1 to 100).foreach { _ => assert(possibleTiles.contains(tile.randomAdjacentTile)) }
  }

  test("shuffling tiles") {
    puzzle.tileAt(4, 3).makeHidden
    puzzle.shuffle()
    assume(puzzle.isSolved === false)
  }

  test("ask tile to go back to initial position") {
    val tile1 = puzzle.tiles(0)
    val tile2 = puzzle.tiles(1)
    tile1.swapPositionWith(tile2)
    tile1.moveToInitialPosition()
    tile2.moveToInitialPosition()

    assert(tile1.isAtInitialPosition)
    assert(tile2.isAtInitialPosition)
  }

  test("revert to initial state") {
    puzzle.tileAt(4, 3).makeHidden
    puzzle.shuffle()
    puzzle.reset()
    assert(puzzle.isSolved)
  }

  test("callback called when tile swapped") {
    val tile = puzzle.tileAt(4, 2)
    var tileModed = false
    puzzle.tileAt(4, 3).makeHidden
    tile.onTileMoved {
      tileModed = true
    }
    tile.moveToEmptySlot()
    assert(tileModed)
  }

  test("callback called when tile moved to initial position") {
    val tile = puzzle.tileAt(4, 2)
    var tileModed = false
    puzzle.tileAt(4, 3).makeHidden
    tile.onTileMoved {
      tileModed = true
    }
    tile.moveToEmptySlot()
    tileModed = false
    tile.moveToInitialPosition
    assert(tileModed)
  }

  test("tile notifies puzzle on every move") {
    var called = false
    val puzzle = new Puzzle(columns = 4, rows = 3) {
      override def didMoveToEmptySlot(tile: Tile) {
        super.didMoveToEmptySlot(tile)
        called = true
      }
    }
    puzzle.tileAt(4, 3).makeHidden()
    puzzle.tileAt(4, 2).moveToEmptySlot()
    assert(called)
  }

  test("tile notifies puzzle on every move to empty slot") {
    var calls = 0
    val puzzle = new Puzzle(columns = 4, rows = 3) {
      override def didMoveToEmptySlot(tile: Tile) {
        super.didMoveToEmptySlot(tile)
        calls += 1
      }
    }
    puzzle.tileAt(4, 3).makeHidden()
    puzzle.tileAt(4, 2).moveToEmptySlot()
    puzzle.reset()
    assert(calls === 1)
  }

  test("callback when puzzle solved") {
    var called = false
    val tile = puzzle.tileAt(4, 2)
    puzzle.tileAt(4, 3).makeHidden
    tile.moveToEmptySlot()
    puzzle.onSolved {
      called = true
    }
    tile.moveToEmptySlot() // make the winning move
    assert(called)
  }

  test("initially, moves are 0") {
    assert(puzzle.movesDone === 0)
  }

  test("one move") {
    val tile = puzzle.tileAt(4, 2)
    puzzle.tileAt(4, 3).makeHidden
    tile.moveToEmptySlot()
    assert(puzzle.movesDone === 1)
  }

  test("shuffling resets the amount of moves") {
    val tile = puzzle.tileAt(4, 2)
    puzzle.tileAt(4, 3).makeHidden
    tile.moveToEmptySlot()
    puzzle.shuffle()
    assert(puzzle.movesDone === 0)
  }

  test("callback when move-count changes") {
    var calls = 0
    val tile = puzzle.tileAt(4, 2)
    puzzle.tileAt(4, 3).makeHidden
    puzzle.onMovesCountChange {
      calls += 1
    }
    tile.moveToEmptySlot()
    puzzle.reset()
    assert(calls === 2)
  }

  test("moves go back to 0 after reset") {
    val tile = puzzle.tileAt(4, 2)
    puzzle.tileAt(4, 3).makeHidden
    tile.moveToEmptySlot()
    puzzle.reset()
    assert(puzzle.movesDone === 0)
  }

  test("setting another tile as hidden unsets the previous one") {
    puzzle.tileAt(4, 2).makeHidden()
    puzzle.tileAt(4, 3).makeHidden()
    assert(puzzle.hiddenTile === puzzle.tileAt(4, 3))
    assert(puzzle.hiddenTile != puzzle.tileAt(4, 2))
  }

  test("tile informs on visibility change (only real changes)") {
    var calls = 0
    val tile = puzzle.tileAt(4, 2)
    tile.onVisibilityChange { toVisible =>
      calls += 1
    }
    tile.makeHidden()
    tile.makeHidden()
    tile.makeVisible()
    tile.makeVisible()

    assert(calls === 2)
  }

}
