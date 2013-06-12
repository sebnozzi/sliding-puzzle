package com.sebnozzi.slidingpuzzle.model

import scala.util.Random

class Tile(val game: Game, val initialPosition: Position) {

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

  def swapPositionWith(other: Tile) {
    val previousPosition = this.currentPosition
    this.currentPosition = other.currentPosition
    other.currentPosition = previousPosition
  }

  def makeHidden() {
    game.setHiddenTileAt(this.currentPosition)
  }

  def makeVisible() {
    game.clearHiddenTile
  }

  def visibilityChanged(toVisible: Boolean) {
    if (_visibilityCallback.isDefined)
      _visibilityCallback.get(toVisible)
  }

  def isAtInitialPosition = (currentPosition == initialPosition)

  def isAdjacentTo(other: Tile) = this.adjacentTiles.contains(other)

  def canMoveToEmptySlot = {
    if (game.hasHiddenTile) {
      val isAdjacentToHiddenTile = this.isAdjacentTo(game.hiddenTile)
      isAdjacentToHiddenTile
    } else {
      false
    }
  }

  def moveToEmptySlot(shuffling: Boolean = false) = {
    if (canMoveToEmptySlot) {
      swapPositionWith(game.hiddenTile)
      if (!shuffling)
        game.didMoveToEmptySlot(this)
      true
    } else {
      false
    }
  }

  def moveToInitialPosition() {
    currentPosition = initialPosition
  }

  def adjacentTiles: List[Tile] = {
    List(
      tileAbove,
      tileLeft,
      tileRight,
      tileBelow).flatten
  }

  def tileAbove: Option[Tile] = 
    currentPosition.aboveIn(game.positionsRect).map{ game.tileAt }
  
  def tileBelow: Option[Tile] = 
        currentPosition.belowIn(game.positionsRect).map{ game.tileAt }

  def tileLeft: Option[Tile] = 
        currentPosition.leftIn(game.positionsRect).map{ game.tileAt }

  def tileRight: Option[Tile] = 
        currentPosition.rightIn(game.positionsRect).map{ game.tileAt }

  def randomAdjacentTile: Tile = {
    val tiles = adjacentTiles
    Random.shuffle(tiles).head
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