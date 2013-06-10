package com.sebnozzi.slidingpuzzle

import scala.util.Random

class Tile(val game: Game, val initialPosition: Position) {

  private var _currentPosition = initialPosition

  def currentPosition = _currentPosition

  def currentPosition_=(newPosition: Position) {
    _currentPosition = newPosition
  }

  def swapPositionWith(other: Tile) {
    val previousPosition = this.currentPosition
    this.currentPosition = other.currentPosition
    other.currentPosition = previousPosition
  }

  def makeHidden {
    game.setHiddenTileAt(this.currentPosition)
  }

  def makeVisible {
    game.clearHiddenTile
  }

  def isAtInitialPosition = (currentPosition == initialPosition)

  def isAdjacentTo(other: Tile) = this.adjacentTiles.contains(other)

  def canMoveToEmptySlot = {
    if (game.hasHiddenTile)
      this.isAdjacentTo(game.hiddenTile)
    else
      false
  }

  def moveToEmptySlot() {
    if (canMoveToEmptySlot)
      swapPositionWith(game.hiddenTile)
  }

  def moveToInitialPosition() {
    _currentPosition = initialPosition
  }
  
  def adjacentTiles: List[Tile] = {
    _currentPosition.adjacentIn(game.positionsRect).map { pos => game.tileAt(pos) }
  }

  def randomAdjacentTile: Tile = {
    val tiles = adjacentTiles
    Random.shuffle(tiles).head
  }

  override def toString() = {
    val positionStr = "ini(%d, %d)|cur(%d, %d)".format(
      initialPosition.col,
      initialPosition.row,
      _currentPosition.col,
      _currentPosition.row)
    s"Tile($positionStr)"
  }
}