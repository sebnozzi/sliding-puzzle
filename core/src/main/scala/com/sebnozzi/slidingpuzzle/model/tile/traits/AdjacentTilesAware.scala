package com.sebnozzi.slidingpuzzle.model.tile.traits

import com.sebnozzi.slidingpuzzle.model.PuzzleTile
import com.sebnozzi.slidingpuzzle.model.structs.{Position, Rect}

import scala.util.Random

trait AdjacentTilesAware { self:PositionAware =>

  protected def positionsRect: Rect
  protected def tileAt(pos: Position): PuzzleTile

  def isAdjacentTo(other: PuzzleTile) = adjacentTiles.contains(other)

  def adjacentTiles: List[PuzzleTile] = List(
    tileAbove,
    tileLeft,
    tileRight,
    tileBelow).flatten

  def tileAbove: Option[PuzzleTile] =
    currentPosition.aboveWithin(positionsRect).map(pos => tileAt(pos))

  def tileBelow: Option[PuzzleTile] =
    currentPosition.belowWithin(positionsRect).map(pos => tileAt(pos))

  def tileLeft: Option[PuzzleTile] =
    currentPosition.leftWithin(positionsRect).map(pos => tileAt(pos))

  def tileRight: Option[PuzzleTile] =
    currentPosition.rightWithin(positionsRect).map(pos => tileAt(pos))

  def randomAdjacentTile: PuzzleTile = {
    val tiles = adjacentTiles
    Random.shuffle(tiles).head
  }

}
