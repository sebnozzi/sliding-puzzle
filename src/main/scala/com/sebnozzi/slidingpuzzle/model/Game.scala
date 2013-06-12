package com.sebnozzi.slidingpuzzle.model

class Game(val columns: Int, val rows: Int) {

  private var _movesDone = 0
  private var _solvedCallback: Option[() => Unit] = None
  private var _hiddenTile: Option[Tile] = None

  val tiles: List[Tile] = {
    (for (
      rowNr <- 1 to rows;
      colNr <- 1 to columns
    ) yield new Tile(game = this, initialPosition = Position(colNr, rowNr))).toList
  }

  val positionsRect = Rect(Position(1, 1), Position(columns, rows))

  def makeRandomMove(times: Int = 1) {
    if (hasHiddenTile) {
      for (_ <- (1 to times)) {
        val tileToMove = hiddenTile.randomAdjacentTile
        tileToMove.moveToEmptySlot()
      }
    }
  }
  
  def movesDone = _movesDone

  protected[model] def tileDidMove(tile: Tile) {
    if (this.isSolved && _solvedCallback.isDefined)
      _solvedCallback.get()
  }

  def onGameSolved(callback: => Unit) {
    _solvedCallback = Some(callback _)
  }

  def reset() {
    tiles.foreach(_.moveToInitialPosition())
    clearHiddenTile()
  }

  def isSolved = {
    tiles.forall { tile => tile.isAtInitialPosition }
  }

  def tileAt(position: Position): Tile = {
    tiles.find(tile => tile.currentPosition == position).get
  }

  def setHiddenTileAt(position: Position) { _hiddenTile = Some(tileAt(position)) }
  def clearHiddenTile() { _hiddenTile = None }

  def hasHiddenTile = _hiddenTile.isDefined

  def hiddenTile: Tile = _hiddenTile.getOrElse(throw new java.util.NoSuchElementException("Does not have a hidden tile"))

}