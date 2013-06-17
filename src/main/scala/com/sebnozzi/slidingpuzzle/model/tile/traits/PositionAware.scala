package com.sebnozzi.slidingpuzzle.model.tile.traits

import com.sebnozzi.slidingpuzzle.model.structs.Position
import com.sebnozzi.slidingpuzzle.model.tile.PuzzleTile

trait PositionAware {

  val initialPosition: Position

  private var _currentPosition = initialPosition
  private var _positionChangeCallback: Option[() => Unit] = None

  def currentPosition = _currentPosition

  def isAtInitialPosition = (currentPosition == initialPosition)

  def currentPosition_=(newPosition: Position) {
    _currentPosition = newPosition
    if (_positionChangeCallback.isDefined)
      _positionChangeCallback.get()
  }

  def moveToInitialPosition() {
    currentPosition = initialPosition
  }

  def onPositionChange(callback: => Unit) {
    _positionChangeCallback = Some(callback _)
  }

  def swapPositionWith(other: PuzzleTile) {
    val previousPosition = PositionAware.this.currentPosition
    PositionAware.this.currentPosition = other.currentPosition
    other.currentPosition = previousPosition
  }

}