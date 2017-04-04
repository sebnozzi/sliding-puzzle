package com.sebnozzi.slidingpuzzle.model

import com.sebnozzi.slidingpuzzle.model.structs.{GridSize, Position, Rect}
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.language.implicitConversions

class puzzleSuite extends FunSuite with BeforeAndAfter {

  implicit def tupleToPosition(tuple: (Int, Int)): Position = Position(tuple._1, tuple._2)

  var puzzle4x3: Puzzle = _
  var puzzle2x2: Puzzle = _
  var puzzle3x3: Puzzle = _

  before {
    puzzle4x3 = new Puzzle(GridSize(columns = 4, rows = 3))
    puzzle2x2 = new Puzzle(GridSize(columns = 2, rows = 2))
    puzzle3x3 = new Puzzle(GridSize(columns = 3, rows = 3))
  }

  test("a puzzle of dimensions 4x3 should have 12 tiles") {
    assert(puzzle4x3.tiles.size === 12)
  }

  test("a puzzle of dimensions 3x3 should have 9 tiles") {
    assert(puzzle3x3.tiles.size === 9)
  }

  test("a tile knows its puzzle") {
    puzzle4x3.tiles.foreach { tile => assert(tile.puzzle === puzzle4x3) }
  }

  test("a puzzle can return a rect for valid positions") {
    assert(puzzle4x3.positionsRect === Rect((1, 1), (4, 3)))
    assert(puzzle2x2.positionsRect === Rect((1, 1), (2, 2)))
  }

  test("it is possible to define a hidden tile") {
    val tile = puzzle4x3.tileAt(4, 3)
    puzzle4x3.setHiddenTileAt(4, 3)
    assert(puzzle4x3.hiddenTile === tile)
  }

  test("moving to empty slot") {
    val tile1 = puzzle4x3.tileAt(4, 2)
    val tile2 = puzzle4x3.tileAt(4, 3)
    tile2.makeHidden()
    tile1.moveToEmptySlot()
    assert(tile1.currentPosition === Position(4, 3))
    assert(tile2.currentPosition === Position(4, 2))
    assert(puzzle4x3.tileAt(4, 2) === tile2)
    assert(puzzle4x3.tileAt(4, 3) === tile1)
  }

  test("moving to empty slot when not adjacent to it should do nothing") {
    val tile1 = puzzle4x3.tileAt(1, 1)
    val tile2 = puzzle4x3.tileAt(4, 3)
    puzzle4x3.setHiddenTileAt(4, 3)
    val result = tile1.moveToEmptySlot()
    assert(result === false)
    assert(tile1.currentPosition === Position(1, 1))
    assert(tile2.currentPosition === Position(4, 3))
  }

  test("asking if a puzzle has a hidden tile") {
    assert(puzzle4x3.hasHiddenTile === false)
    puzzle4x3.setHiddenTileAt(4, 3)
    assert(puzzle4x3.hasHiddenTile === true)
  }

  test("initially, puzzle is in a solved state") {
    assert(puzzle4x3.isSolved)
  }

  test("as soon as one move is made, the puzzle is not in solved state") {
    val tile1 = puzzle4x3.tileAt(4, 2)
    val tile2 = puzzle4x3.tileAt(4, 3)
    tile2.makeHidden
    tile1.moveToEmptySlot()
    assert(puzzle4x3.isSolved === false)
  }

  test("puzzle solved after putting back one moved tile") {
    val tile = puzzle4x3.tileAt(4, 2)
    val hiddenTile = puzzle4x3.tileAt(4, 3)
    hiddenTile.makeHidden
    val firstMoveDone = tile.moveToEmptySlot()
    val secondMoveDone = tile.moveToEmptySlot()
    assert(firstMoveDone)
    assert(secondMoveDone)
    assert(puzzle4x3.isSolved)
  }

  test("shuffling tiles should result in unsolved state") {
    puzzle4x3.tileAt(4, 3).makeHidden
    puzzle4x3.shuffle()
    assume(puzzle4x3.isSolved === false)
  }

  test("resetting the puzzle should leave it in solved state") {
    puzzle4x3.tileAt(4, 3).makeHidden
    puzzle4x3.shuffle()
    puzzle4x3.reset()
    assert(puzzle4x3.isSolved)
  }

  test("callback when puzzle solved") {
    var called = false
    val tile = puzzle4x3.tileAt(4, 2)
    puzzle4x3.tileAt(4, 3).makeHidden
    tile.moveToEmptySlot()
    puzzle4x3.onSolved {
      called = true
    }
    tile.moveToEmptySlot() // make the winning move
    assert(called)
  }

  test("initially, moves are at 0") {
    assert(puzzle4x3.movesDone === 0)
  }

  test("after one move is made, the counter should reflect it") {
    val tile = puzzle4x3.tileAt(4, 2)
    puzzle4x3.tileAt(4, 3).makeHidden
    tile.moveToEmptySlot()
    assert(puzzle4x3.movesDone === 1)
  }

  test("shuffling resets the amount of moves") {
    val tile = puzzle4x3.tileAt(4, 2)
    puzzle4x3.tileAt(4, 3).makeHidden
    tile.moveToEmptySlot()
    puzzle4x3.shuffle()
    assert(puzzle4x3.movesDone === 0)
  }

  test("callback when move-count changes") {
    var calls = 0
    val tile = puzzle4x3.tileAt(4, 2)
    puzzle4x3.tileAt(4, 3).makeHidden
    puzzle4x3.onMovesCountChange {
      calls += 1
    }
    tile.moveToEmptySlot()
    puzzle4x3.reset()
    assert(calls === 2)
  }

  test("moves go back to 0 after reset") {
    val tile = puzzle4x3.tileAt(4, 2)
    puzzle4x3.tileAt(4, 3).makeHidden
    tile.moveToEmptySlot()
    puzzle4x3.reset()
    assert(puzzle4x3.movesDone === 0)
  }

}
