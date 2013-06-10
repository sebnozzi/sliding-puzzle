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
  
  test("if within rect"){
    val pos1 = Position(col = 1, row = 1)
    val pos2 = Position(col = 2, row = 2)
    val pos3 = Position(col = 2, row = 3)
    val pos4 = Position(col = 4, row = 3)
    val rect = Rect(topLeft=Position(1,1), bottomRight=Position(2,2))
    
    assert(rect.contains(pos1))
    assert(rect.contains(pos2))
    assert(!rect.contains(pos3))
    assert(!rect.contains(pos4))
    
  }
  
  test("adjacent positions of 1,1 (top left corner)") {
    val pos1 = Position(col = 1, row = 1)
    assert(pos1.adjacentIn(Rect(Position(1,1), Position(4,3))) === List(Position(2,1), Position(1,2)))
  }  
  
  test("adjacent positions of 4,3 (bottom right corner)") {
    val pos1 = Position(col = 4, row = 3)
    assert(pos1.adjacentIn(Rect(Position(1,1), Position(4,3))) === List(Position(4,2), Position(3,3)))
  }    
  
}
