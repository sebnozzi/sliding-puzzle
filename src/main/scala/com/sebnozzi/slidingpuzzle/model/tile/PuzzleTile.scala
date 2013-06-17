package com.sebnozzi.slidingpuzzle.model.tile

import com.sebnozzi.slidingpuzzle.model.structs.Position
import com.sebnozzi.slidingpuzzle.model.Puzzle
import com.sebnozzi.slidingpuzzle.model.tile.traits.VisibilityAware
import com.sebnozzi.slidingpuzzle.model.tile.traits.PositionAware
import com.sebnozzi.slidingpuzzle.model.tile.traits.AdjacentTilesAware

class PuzzleTile(val puzzle: Puzzle, val initialPosition: Position)
  extends PositionAware with AdjacentTilesAware with VisibilityAware {

  protected def positionsRect = puzzle.positionsRect
  protected def tileAt(pos: Position) = puzzle.tileAt(pos)
  
  def makeHidden() {
    puzzle.setHiddenTileAt(PuzzleTile.this.currentPosition)
  }

  def makeVisible() {
    puzzle.clearHiddenTile
  }  
  
  def canMoveToEmptySlot = {
    puzzle.canMoveToEmptySlot(this)
  }

  def moveToEmptySlot(shuffling: Boolean = false) = {
    puzzle.moveToEmptySlot(this, shuffling)
  }
  
  override def toString() = {
    val positionStr = "ini(%d, %d)|cur(%d, %d)".format(
      initialPosition.col,
      initialPosition.row,
      currentPosition.col,
      currentPosition.row)
    s"Tile($positionStr)"
  }

}