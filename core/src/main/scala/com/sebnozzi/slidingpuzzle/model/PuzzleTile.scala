package com.sebnozzi.slidingpuzzle.model

import com.sebnozzi.slidingpuzzle.model.structs.{Position, Rect}
import com.sebnozzi.slidingpuzzle.model.tile.traits._

class PuzzleTile(val puzzle: Puzzle, val initialPosition: Position)
  extends PositionAware with AdjacentTilesAware with VisibilityAware {

  protected def positionsRect: Rect = puzzle.positionsRect
  protected def tileAt(pos: Position): PuzzleTile = puzzle.tileAt(pos)
  
  def makeHidden(): Unit = {
    puzzle.setHiddenTileAt(PuzzleTile.this.currentPosition)
  }

  def makeVisible(): Unit = {
    puzzle.clearHiddenTile()
  }  
  
  def canMoveToEmptySlot: Boolean = {
    puzzle.canMoveToEmptySlot(this)
  }

  def moveToEmptySlot(shuffling: Boolean = false): Boolean = {
    puzzle.moveToEmptySlot(this, shuffling)
  }
  
  override def toString: String = {
    val positionStr = "ini(%d, %d)|cur(%d, %d)".format(
      initialPosition.col,
      initialPosition.row,
      currentPosition.col,
      currentPosition.row)
    s"Tile($positionStr)"
  }

}