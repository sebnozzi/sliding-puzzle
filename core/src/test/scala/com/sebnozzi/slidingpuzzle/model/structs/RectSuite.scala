package com.sebnozzi.slidingpuzzle.model.structs

import org.scalatest.FunSuite

class RectSuite extends FunSuite {

  test("if within rect") {
    val pos1 = Position(col = 1, row = 1)
    val pos2 = Position(col = 2, row = 2)
    val pos3 = Position(col = 2, row = 3)
    val pos4 = Position(col = 4, row = 3)
    val rect = Rect(topLeft = Position(1, 1), bottomRight = Position(2, 2))

    assert(rect.contains(pos1))
    assert(rect.contains(pos2))
    assert(!rect.contains(pos3))
    assert(!rect.contains(pos4))
  }

}
