package com.sebnozzi.slidingpuzzle

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

  def adjacentTiles: List[Tile] = {
    _currentPosition.adjacentIn(???)
    ???
  }
  
  override def toString() = {
    val positionStr = "(%d, %d)".format(_currentPosition.col, _currentPosition.row)
    s"Tile$positionStr"
  }
}