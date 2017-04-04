package com.sebnozzi.slidingpuzzle.model.tile.traits

import com.sebnozzi.slidingpuzzle.model.structs.Position
import org.scalatest.{BeforeAndAfter, FunSuite}

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

  def testShufflingState(expectedState: Boolean) {
    var wasShuffling1 = false
    var wasShuffling2 = false
    tile1.onPositionChange((shuffling) => { wasShuffling1 = shuffling })
    tile2.onPositionChange((shuffling) => { wasShuffling2 = shuffling })
    tile1.swapPositionWith(tile2, shuffling = expectedState)
    assert(wasShuffling1 === expectedState)
    assert(wasShuffling2 === expectedState)
  }
  test("should passe shuffling state (true) to callback") {
    testShufflingState(expectedState=true)
  }
  test("should passe shuffling state (false) to callback") {
    testShufflingState(expectedState=false)
  }

  test("after swapping once, the tile should not be at its initial position") {
    assert(tile1.isAtInitialPosition)
    tile1.swapPositionWith(tile2)
    assert(tile1.isAtInitialPosition === false)
  }

  test("ask tile to go back to initial position") {
    tile1.swapPositionWith(tile2)
    tile1.moveToInitialPosition()
    tile2.moveToInitialPosition()

    assert(tile1.isAtInitialPosition)
    assert(tile2.isAtInitialPosition)
  }

  test("callback called when tile swapped") {
    var tileMoved = false
    tile1.onPositionChange { shuffling: Boolean =>
      tileMoved = true
    }
    tile1.swapPositionWith(tile2)
    assert(tileMoved)
  }

  test("callback called when tile moved to initial position") {
    var tileMoved = false
    tile1.swapPositionWith(tile2)
    assert(!tileMoved, "callback should not have been called yet")
    tile1.onPositionChange { shuffling: Boolean =>
      tileMoved = true
    }
    tile1.moveToInitialPosition
    assert(tileMoved, "callback should have been called after moving to initial position")
  }

}
