package com.sebnozzi.slidingpuzzle.model.structs

case class Rect(topLeft: Position, bottomRight: Position) {

  def contains(pos: Position): Boolean =
    (pos.col >= topLeft.col && pos.col <= bottomRight.col) &&
      (pos.row >= topLeft.row && pos.row <= bottomRight.row)

}
