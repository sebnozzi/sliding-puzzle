package com.sebnozzi.slidingpuzzle.model

package object PositionConversions {
  implicit def tupleToPosition(tuple: Pair[Int, Int]): Position = Position(tuple._1, tuple._2)
}

case class Position(col: Int, row: Int) {

  lazy val above = Position(col, row - 1)
  lazy val below = Position(col, row + 1)
  lazy val left = Position(col - 1, row)
  lazy val right = Position(col + 1, row)

  def adjacentIn(rect: Rect): List[Position] = {
    List(above, left, right, below).filter { rect.contains(_) }
  }

  def aboveIn(rect: Rect): Option[Position] = above.optionallyWithin(rect)

  def belowIn(rect: Rect): Option[Position] = below.optionallyWithin(rect)

  def leftIn(rect: Rect): Option[Position] = left.optionallyWithin(rect)

  def rightIn(rect: Rect): Option[Position] = right.optionallyWithin(rect)

  private def optionallyWithin(rect: Rect): Option[Position] = {
    if (rect.contains(this))
      Some(this)
    else
      None
  }

}