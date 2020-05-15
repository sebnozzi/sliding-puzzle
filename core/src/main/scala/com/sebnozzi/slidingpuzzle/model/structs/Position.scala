package com.sebnozzi.slidingpuzzle.model.structs

case class Position(col: Int, row: Int) {

  lazy val above: Position = Position(col, row - 1)
  lazy val below: Position = Position(col, row + 1)
  lazy val left: Position = Position(col - 1, row)
  lazy val right: Position = Position(col + 1, row)

  def adjacentPositionsWithin(rect: Rect): Seq[Position] =
    Seq(above, left, right, below)
      .filter( pos => rect.contains(pos) )

  def aboveWithin(rect: Rect): Option[Position] =
    above.optionallyWithin(rect)

  def belowWithin(rect: Rect): Option[Position] =
    below.optionallyWithin(rect)

  def leftWithin(rect: Rect): Option[Position] =
    left.optionallyWithin(rect)

  def rightWithin(rect: Rect): Option[Position] =
    right.optionallyWithin(rect)

  private def optionallyWithin(rect: Rect): Option[Position] = {
    if (rect.contains(this))
      Some(this)
    else
      None
  }

}
