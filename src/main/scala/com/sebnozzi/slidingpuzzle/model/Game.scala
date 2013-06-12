package com.sebnozzi.slidingpuzzle.model

class Game(val columns: Int, val rows: Int) {

  private var _movesDone = 0
  private var _solvedCallback: Option[() => Unit] = None
  private var _movesCountCallback: Option[() => Unit] = None
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

  private def movesDone_=(newCount: Int) {
    println("updating to " + newCount)
    _movesDone = newCount
    if (_movesCountCallback.isDefined)
      _movesCountCallback.get()
  }

  def didMoveToEmptySlot(tile: Tile) {
    movesDone = movesDone + 1
    if (this.isSolved && _solvedCallback.isDefined)
      _solvedCallback.get()
  }

  def onGameSolved(callback: => Unit) {
    _solvedCallback = Some(callback _)
  }

  def onMovesCountChange(callback: => Unit) {
    _movesCountCallback = Some(callback _)
  }

  def reset() {
    tiles.foreach(_.moveToInitialPosition())
    movesDone = 0
    clearHiddenTile()
  }

  def isSolved = {
    tiles.forall { tile => tile.isAtInitialPosition }
  }

  def tileAt(position: Position): Tile = {
    tiles.find(tile => tile.currentPosition == position).get
  }

  def setHiddenTileAt(position: Position) {
    val newTile = tileAt(position)
    _hiddenTile match {
      case Some(currentTile) if newTile != currentTile => {
        currentTile.visibilityChanged()
        _hiddenTile = Some(newTile)
        newTile.visibilityChanged()
      }
      case None => {
        _hiddenTile = Some(newTile)
        newTile.visibilityChanged()
      }
      case _ => {}
    }
  }

  def clearHiddenTile() {
    _hiddenTile.map(_.visibilityChanged())
    _hiddenTile = None
  }

  def hasHiddenTile = _hiddenTile.isDefined

  def hiddenTile: Tile = _hiddenTile.getOrElse(throw new java.util.NoSuchElementException("Does not have a hidden tile"))

}