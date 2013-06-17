package com.sebnozzi.slidingpuzzle.model

import scala.util.Random
import com.sebnozzi.slidingpuzzle.model.structs.Position

class PuzzleTile(val puzzle: Puzzle, val initialPosition: Position) extends OtherTilesAware {

  private var _currentPosition = initialPosition
  private var _tileMovedCallback: Option[() => Unit] = None
  private var _visibilityCallback: Option[(Boolean) => Unit] = None

  def currentPosition = _currentPosition

  def currentPosition_=(newPosition: Position) {
    _currentPosition = newPosition
    if (_tileMovedCallback.isDefined)
      _tileMovedCallback.get()
  }

  def onTileMoved(callback: => Unit) {
    _tileMovedCallback = Some(callback _)
  }

  def onVisibilityChange(callback: (Boolean) => Unit) {
    _visibilityCallback = Some(callback)
  }

  def swapPositionWith(other: PuzzleTile) {
    val previousPosition = this.currentPosition
    this.currentPosition = other.currentPosition
    other.currentPosition = previousPosition
  }

  def makeHidden() {
    puzzle.setHiddenTileAt(PuzzleTile.this.currentPosition)
  }

  def makeVisible() {
    puzzle.clearHiddenTile
  }

  def visibilityChanged(toVisible: Boolean) {
    if (_visibilityCallback.isDefined)
      _visibilityCallback.get(toVisible)
  }

  def isAtInitialPosition = (currentPosition == initialPosition)

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

  def moveToInitialPosition() {
    currentPosition = initialPosition
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