package com.sebnozzi.slidingpuzzle

class Tile(val game: SlidingPuzzleGame, val initialPosition: Position) {

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
    List(
      new Tile(game, Position(2, 1)),
      new Tile(game, Position(1, 2)))
  }

}