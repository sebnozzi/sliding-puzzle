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
      if (!isAdjacentToHiddenTile) println("Not adjacent to hidden tile")
      isAdjacentToHiddenTile
    } else {
      println("Game does not have a hidden tile")
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
    currentPosition.adjacentIn(game.positionsRect).map { pos => game.tileAt(pos) }
  }

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