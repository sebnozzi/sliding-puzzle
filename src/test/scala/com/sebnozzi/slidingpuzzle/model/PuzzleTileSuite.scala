package com.sebnozzi.slidingpuzzle.model

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import com.sebnozzi.slidingpuzzle.model.structs.GridSize

class PuzzleTileSuite extends FunSuite with BeforeAndAfter {

  var puzzle4x3: Puzzle = _

  before {
    puzzle4x3 = new Puzzle(GridSize(columns = 4, rows = 3))
  }

  test("a tile knows its puzzle") {
    puzzle4x3.tiles.foreach { tile => assert(tile.puzzle === puzzle4x3) }
  }

}