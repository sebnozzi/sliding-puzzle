package com.sebnozzi.slidingpuzzle.model.tile.traits

import com.sebnozzi.slidingpuzzle.model.structs.Position

trait PositionAware {

  val initialPosition: Position

  private var _currentPosition = initialPosition
  private var _positionChangeCallback: Option[Boolean => Unit] = None

  def currentPosition: Position = _currentPosition

  def isAtInitialPosition: Boolean = {
    currentPosition == initialPosition
  }

  def currentPosition_=(newPosition: Position, shuffling: Boolean = false): Unit = {
    _currentPosition = newPosition
    _positionChangeCallback.foreach(callback => callback(shuffling))
  }

  def moveToInitialPosition(): Unit = {
    currentPosition = initialPosition
  }

  def onPositionChange(callback: Boolean => Unit): Unit = {
    _positionChangeCallback = Some(callback)
  }

  def swapPositionWith(other: PositionAware, shuffling: Boolean = false): Unit = {
    val previousPosition = currentPosition
    currentPosition_=(other.currentPosition, shuffling)
    other.currentPosition_=(previousPosition, shuffling)
  }

}
