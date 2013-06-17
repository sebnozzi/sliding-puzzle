package com.sebnozzi.slidingpuzzle.model

import com.sebnozzi.slidingpuzzle.model.structs.Position

trait Tile {

  val initialPosition: Position  
  
  private var _currentPosition = initialPosition
  private var _tileMovedCallback: Option[() => Unit] = None

  def currentPosition = _currentPosition

  def isAtInitialPosition = (currentPosition == initialPosition)
  
  def currentPosition_=(newPosition: Position) {
    _currentPosition = newPosition
    if (_tileMovedCallback.isDefined)
      _tileMovedCallback.get()
  }

  def moveToInitialPosition() {
    currentPosition = initialPosition
  }  
  
  def onTileMoved(callback: => Unit) {
    _tileMovedCallback = Some(callback _)
  }

  def swapPositionWith(other: PuzzleTile) {
    val previousPosition = this.currentPosition
    this.currentPosition = other.currentPosition
    other.currentPosition = previousPosition
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