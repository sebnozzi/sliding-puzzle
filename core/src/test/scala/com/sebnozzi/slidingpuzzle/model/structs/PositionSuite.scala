package com.sebnozzi.slidingpuzzle.model.structs

import org.scalatest.FunSuite

class PositionSuite extends FunSuite {

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
    assert(pos.adjacentIn(rect) === List(Position(2, 1), Position(1, 2)))
  }

  test("adjacent positions of 4,3 (bottom right corner)") {
    val pos = Position(col = 4, row = 3)
    val rect = Rect(Position(1, 1), Position(4, 3))
    assert(pos.adjacentIn(rect) === List(Position(4, 2), Position(3, 3)))
  }

  test("named adjacent positions of 1,1") {
    val pos = Position(col = 1, row = 1)
    val rect = Rect(Position(1, 1), Position(4, 3))
    assert(pos.aboveIn(rect) === None)
    assert(pos.belowIn(rect) === Some(Position(1, 2)))
    assert(pos.leftIn(rect) === None)
    assert(pos.rightIn(rect) === Some(Position(2, 1)))
  }

}
