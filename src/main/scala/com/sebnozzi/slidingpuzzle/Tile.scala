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
  
  def makeHidden {
    game.setHiddenTileAt(this.currentPosition)
  }
  
  def isAdjacentTo(other:Tile) = this.adjacentTiles.contains(other)
  
  def canMoveToEmptySlot = {
    this.isAdjacentTo(game.hiddenTile)
  }

  def moveToEmptySlot() {
    if(canMoveToEmptySlot)
      swapPositionWith(game.hiddenTile)
  }  
  
  def adjacentTiles: List[Tile] = {
    _currentPosition.adjacentIn(game.positionsRect).map{pos => game.tileAt(pos)}
  }
   
  override def toString() = {
    val positionStr = "(%d, %d)".format(_currentPosition.col, _currentPosition.row)
    s"Tile$positionStr"
  }
}