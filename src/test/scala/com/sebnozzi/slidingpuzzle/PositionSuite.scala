package com.sebnozzi.slidingpuzzle

import org.scalatest.FunSuite

class PositionSuite extends FunSuite  {

  test("a Position consists of column and row") {
    val pos1 = Position(col = 1, row = 1)
    val pos2 = Position(col = 2, row = 3)
    assert(pos1.col === 1)
    assert(pos1.row === 1)
    assert(pos2.col === 2)
    assert(pos2.row === 3)
  }
  
  test("a Position knows adjacent positions to itself") {
    val pos1 = Position(col = 1, row = 1)
    assert(pos1.adjacent === List(Position(2,1), Position(1,2)))
  }  
  
}
