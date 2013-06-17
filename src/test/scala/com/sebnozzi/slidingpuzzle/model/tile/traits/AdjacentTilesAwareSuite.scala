package com.sebnozzi.slidingpuzzle.model.tile.traits

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import com.sebnozzi.slidingpuzzle.model.Puzzle
import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.model.structs.Position

class AdjacentTilesAwareSuite extends FunSuite with BeforeAndAfter {

  var puzzle2x2: Puzzle = _
  var puzzle3x3: Puzzle = _

  before {
    puzzle2x2 = new Puzzle(GridSize(columns = 2, rows = 2))
    puzzle3x3 = new Puzzle(GridSize(columns = 3, rows = 3))
  }
  
  test("a tile knows its adjacent tiles") {
    val topLeft = puzzle2x2.tileAt(Position(1, 1))
    val topRight = puzzle2x2.tileAt(Position(2, 1))
    val bottomLeft = puzzle2x2.tileAt(Position(1, 2))
    val bottomRight = puzzle2x2.tileAt(Position(2, 2))
    assert(topLeft.adjacentTiles === List(topRight, bottomLeft))
    assert(topRight.adjacentTiles === List(topLeft, bottomRight))
  }

  test("a tile knows directional adjacent tiles") {
    val tile = puzzle3x3.tileAt(Position(1, 2))
    assert(tile.tileAbove.get.initialPosition === Position(1, 1))
    assert(tile.tileRight.get.initialPosition === Position(2, 2))
    assert(tile.tileBelow.get.initialPosition === Position(1, 3))
    assert(tile.tileLeft === None)
  }  
  
}