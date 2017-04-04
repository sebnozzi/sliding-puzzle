package com.sebnozzi.slidingpuzzle.model

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import org.scalatest.{BeforeAndAfter, FunSuite}

class PuzzleTileSuite extends FunSuite with BeforeAndAfter {

  var puzzle4x3: Puzzle = _

  before {
    puzzle4x3 = new Puzzle(GridSize(columns = 4, rows = 3))
  }

  test("a puzzle-tile knows its puzzle") {
    puzzle4x3.tiles.foreach { tile => assert(tile.puzzle === puzzle4x3) }
  }

  test("moving to empty slot, when no tile is hidden, should be false") {
    val tile1 = puzzle4x3.tileAt(3, 3)
    val tile2 = puzzle4x3.tileAt(4, 3)
    assert(tile1.canMoveToEmptySlot === false)
  }

  test("making a tile hidden") {
    val tile1 = puzzle4x3.tileAt(1, 1)
    tile1.makeHidden
    assert(puzzle4x3.hiddenTile === tile1)
  }

  test("unhiding the tile") {
    assert(puzzle4x3.hasHiddenTile === false)
    val tile = puzzle4x3.tileAt(1, 1)
    tile.makeHidden
    assert(puzzle4x3.hasHiddenTile)
    tile.makeVisible
    assert(puzzle4x3.hasHiddenTile === false)
  }

  test("a tile non-adjacent to the hidden one can not be moved") {
    val tile1 = puzzle4x3.tileAt(1, 1)
    val tile2 = puzzle4x3.tileAt(4, 3)
    puzzle4x3.setHiddenTileAt(4, 3)
    assert(tile1.canMoveToEmptySlot === false)
  }

  test("tile notifies puzzle on every move") {
    var called = false
    val puzzle = new Puzzle(GridSize(columns = 4, rows = 3)) {
      override def didMoveToEmptySlot(tile: PuzzleTile) {
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
    val puzzle = new Puzzle(GridSize(columns = 4, rows = 3)) {
      override def didMoveToEmptySlot(tile: PuzzleTile) {
        super.didMoveToEmptySlot(tile)
        calls += 1
      }
    }
    puzzle.tileAt(4, 3).makeHidden()
    puzzle.tileAt(4, 2).moveToEmptySlot()
    puzzle.reset()
    assert(calls === 1)
  }

  test("setting another tile as hidden unsets the previous one") {
    puzzle4x3.tileAt(4, 2).makeHidden()
    puzzle4x3.tileAt(4, 3).makeHidden()
    assert(puzzle4x3.hiddenTile === puzzle4x3.tileAt(4, 3))
    assert(puzzle4x3.hiddenTile != puzzle4x3.tileAt(4, 2))
  }

  test("tile is informed on visibility change (only real changes)") {
    var calls = 0
    val tile = puzzle4x3.tileAt(4, 2)
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
