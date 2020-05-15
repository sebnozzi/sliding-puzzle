package com.sebnozzi.slidingpuzzle.model.structs

import org.scalatest.funsuite.AnyFunSuite


class PositionSuite extends AnyFunSuite {

  test("a Position consists of column and row") {
    val pos1 = Position(col = 1, row = 1)
    val pos2 = Position(col = 2, row = 3)
    assert(pos1.col === 1)
    assert(pos1.row === 1)
    assert(pos2.col === 2)
    assert(pos2.row === 3)
  }

  test("adjacent positions of 1,1 (top left corner)") {
    val pos = Position(col = 1, row = 1)
    val rect = Rect(Position(1, 1), Position(4, 3))
    assert(pos.adjacentPositionsWithin(rect) === List(Position(2, 1), Position(1, 2)))
  }

  test("adjacent positions of 4,3 (bottom right corner)") {
    val pos = Position(col = 4, row = 3)
    val rect = Rect(Position(1, 1), Position(4, 3))
    assert(pos.adjacentPositionsWithin(rect) === List(Position(4, 2), Position(3, 3)))
  }

  test("named adjacent positions of 1,1") {
    val pos = Position(col = 1, row = 1)
    val rect = Rect(Position(1, 1), Position(4, 3))
    assert(pos.aboveWithin(rect) === None)
    assert(pos.belowWithin(rect) === Some(Position(1, 2)))
    assert(pos.leftWithin(rect) === None)
    assert(pos.rightWithin(rect) === Some(Position(2, 1)))
  }

}
