package com.sebnozzi.slidingpuzzle

case class Position(col: Int, row: Int) {

  def adjacentIn(rect: Rect): List[Position] = {

    val above = Position(col, row - 1)
    val below = Position(col, row + 1)
    val left = Position(col - 1, row)
    val right = Position(col + 1, row)

    List(above, left, right, below).filter { rect.contains(_) }
  }

}