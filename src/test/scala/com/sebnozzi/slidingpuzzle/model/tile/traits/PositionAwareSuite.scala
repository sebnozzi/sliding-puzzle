package com.sebnozzi.slidingpuzzle.model.tile.traits

import org.scalatest.FunSuite
import com.sebnozzi.slidingpuzzle.model.structs.Position
import org.scalatest.BeforeAndAfter

class PositionAwareSuite extends FunSuite with BeforeAndAfter {

  class TestTile(val initialPosition: Position) extends PositionAware {}

  var tile1: TestTile = _
  var tile2: TestTile = _

  before {
    tile1 = new TestTile(Position(1, 1))
    tile2 = new TestTile(Position(4, 3))
  }

  test("a tile has an original position") {
    assert(tile1.initialPosition === Position(1, 1))
    assert(tile2.initialPosition === Position(4, 3))
  }

  test("a tile's current position is initially the initial position") {
    assert(tile1.currentPosition === tile1.initialPosition)
  }

  test("changing a tile's current position") {
    tile1.currentPosition = Position(2, 2)
    assert(tile1.currentPosition != tile1.initialPosition)
  }

  test("swapping positions between two tiles") {
    tile1.swapPositionWith(tile2)
    assert(tile1.currentPosition === tile2.initialPosition)
    assert(tile2.currentPosition === tile1.initialPosition)
  }

}