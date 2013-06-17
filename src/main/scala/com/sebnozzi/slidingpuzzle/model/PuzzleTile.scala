package com.sebnozzi.slidingpuzzle.model

import scala.util.Random
import com.sebnozzi.slidingpuzzle.model.structs.Position

class PuzzleTile(val puzzle: Puzzle, val initialPosition: Position)
  extends Tile with OtherTilesAware with VisibilityAware {

  def canMoveToEmptySlot = {
    if (puzzle.hasHiddenTile) {
      PuzzleTile.this.isAdjacentTo(puzzle.hiddenTile)
    } else {
      false
    }
  }

  def moveToEmptySlot(shuffling: Boolean = false) = {
    if (canMoveToEmptySlot) {
      swapPositionWith(puzzle.hiddenTile)
      if (!shuffling)
        puzzle.didMoveToEmptySlot(PuzzleTile.this)
      true
    } else {
      false
    }
  }

}