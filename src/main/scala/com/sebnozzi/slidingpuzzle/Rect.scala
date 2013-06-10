package com.sebnozzi.slidingpuzzle

case class Rect(topLeft: Position, bottomRight: Position) {

  def contains(pos: Position) = {
    (pos.col >= topLeft.col && pos.col <= bottomRight.col) &&
      (pos.row >= topLeft.row && pos.row <= bottomRight.row)
  }

}