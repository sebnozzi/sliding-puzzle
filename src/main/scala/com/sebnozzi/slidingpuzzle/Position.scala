package com.sebnozzi.slidingpuzzle

case class Position(col: Int, row: Int) {

  def adjacent: List[Position] = List(
    Position(2, 1), Position(1, 2))

}