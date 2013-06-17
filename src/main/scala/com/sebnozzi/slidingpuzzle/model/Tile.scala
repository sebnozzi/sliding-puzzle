package com.sebnozzi.slidingpuzzle.model

import scala.util.Random

class Tile(val puzzle: Puzzle, val initialPosition: Position) {

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
    puzzle.setHiddenTileAt(this.currentPosition)
  }

  def makeVisible() {
    puzzle.clearHiddenTile
  }

  def visibilityChanged(toVisible: Boolean) {
    if (_visibilityCallback.isDefined)
      _visibilityCallback.get(toVisible)
  }

  def isAtInitialPosition = (currentPosition == initialPosition)

  def isAdjacentTo(other: Tile) = this.adjacentTiles.contains(other)

  def canMoveToEmptySlot = {
    if (puzzle.hasHiddenTile) {
      this.isAdjacentTo(puzzle.hiddenTile)
    } else {
      false
    }
  }

  def moveToEmptySlot(shuffling: Boolean = false) = {
    if (canMoveToEmptySlot) {
      swapPositionWith(puzzle.hiddenTile)
      if (!shuffling)
        puzzle.didMoveToEmptySlot(this)
      true
    } else {
      false
    }
  }

  def moveToInitialPosition() {
    currentPosition = initialPosition
  }

  def adjacentTiles: List[Tile] = List(
    tileAbove,
    tileLeft,
    tileRight,
    tileBelow).flatten

  def tileAbove: Option[Tile] =
    currentPosition.aboveIn(puzzle.positionsRect).map { puzzle.tileAt }

  def tileBelow: Option[Tile] =
    currentPosition.belowIn(puzzle.positionsRect).map { puzzle.tileAt }

  def tileLeft: Option[Tile] =
    currentPosition.leftIn(puzzle.positionsRect).map { puzzle.tileAt }

  def tileRight: Option[Tile] =
    currentPosition.rightIn(puzzle.positionsRect).map { puzzle.tileAt }

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