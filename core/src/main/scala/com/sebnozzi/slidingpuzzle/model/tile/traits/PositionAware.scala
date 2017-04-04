package com.sebnozzi.slidingpuzzle.model.tile.traits

import com.sebnozzi.slidingpuzzle.model.structs.Position

trait PositionAware {

  val initialPosition: Position

  private var _currentPosition = initialPosition
  private var _positionChangeCallback: Option[(Boolean) => Unit] = None

  def currentPosition = _currentPosition

  def isAtInitialPosition = (currentPosition == initialPosition)

  def currentPosition_=(newPosition: Position, shuffling: Boolean = false) {
    _currentPosition = newPosition
    _positionChangeCallback.foreach(callback => callback(shuffling))
  }

  def moveToInitialPosition() {
    currentPosition = initialPosition
  }

  def onPositionChange(callback: (Boolean) => Unit) {
    _positionChangeCallback = Some(callback)
  }

  def swapPositionWith(other: PositionAware, shuffling: Boolean = false) {
    val previousPosition = currentPosition
    currentPosition_=(other.currentPosition, shuffling)
    other.currentPosition_=(previousPosition, shuffling)
  }

}
