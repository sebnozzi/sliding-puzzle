package com.sebnozzi.slidingpuzzle.model

import scala.util.Random

trait OtherTilesAware { self:PuzzleTile => 

 def adjacentTiles: List[PuzzleTile] = List(
    tileAbove,
    tileLeft,
    tileRight,
    tileBelow).flatten
    
  private def positionsRect = puzzle.positionsRect
  private def tileAt(pos:Position) = puzzle.tileAt(pos)
  
  def tileAbove: Option[PuzzleTile] =
    currentPosition.aboveIn(positionsRect).map { tileAt }

  def tileBelow: Option[PuzzleTile] =
    currentPosition.belowIn(positionsRect).map { tileAt }

  def tileLeft: Option[PuzzleTile] =
    currentPosition.leftIn(positionsRect).map { tileAt }

  def tileRight: Option[PuzzleTile] =
    currentPosition.rightIn(positionsRect).map { tileAt }

  def randomAdjacentTile: PuzzleTile = {
    val tiles = adjacentTiles
    Random.shuffle(tiles).head
  }  
  
}