package com.sebnozzi.slidingpuzzle.model

import com.sebnozzi.slidingpuzzle.model.structs.{GridSize, Position, Rect}

class Puzzle(gridSize: GridSize) {

  private var _movesDone = 0
  private var _solvedCallback: Option[() => Unit] = None
  private var _movesCountCallback: Option[() => Unit] = None
  private var _hiddenTile: Option[PuzzleTile] = None

  val columns = gridSize.columns
  val rows = gridSize.rows

  val tiles: List[PuzzleTile] = {
    (for (
      rowNr <- 1 to rows;
      colNr <- 1 to columns
    ) yield new PuzzleTile(puzzle = this, initialPosition = Position(colNr, rowNr))).toList
  }

  val positionsRect = Rect(Position(1, 1), Position(columns, rows))

  private def makeRandomMove(times: Int = 1) {
    if (hasHiddenTile) {
      for (_ <- (1 to times)) {
        val tileToMove = hiddenTile.randomAdjacentTile
        tileToMove.moveToEmptySlot(shuffling = true)
      }
    }
  }

  def shuffle() {
    makeRandomMove(times = 300)
    movesDone = 0
  }

  def movesDone = _movesDone

  private def movesDone_=(newCount: Int) {
    _movesDone = newCount
    if (_movesCountCallback.isDefined)
      _movesCountCallback.get()
  }

  def canMoveToEmptySlot(tile: PuzzleTile) = {
    if (hasHiddenTile) {
      tile.isAdjacentTo(hiddenTile)
    } else {
      false
    }
  }

  def moveToEmptySlot(tile: PuzzleTile, shuffling: Boolean = false) = {
    if (canMoveToEmptySlot(tile)) {
      tile.swapPositionWith(hiddenTile, shuffling)
      if (!shuffling)
        didMoveToEmptySlot(tile)
      true
    } else {
      false
    }
  }

  def didMoveToEmptySlot(tile: PuzzleTile) {
    movesDone = movesDone + 1
    if (this.isSolved && _solvedCallback.isDefined)
      _solvedCallback.get()
  }

  def onSolved(callback: => Unit) {
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

  def tileAt(position: Position): PuzzleTile = {
    tiles.find(tile => tile.currentPosition == position).get
  }

  def tileAt(col: Int, row: Int): PuzzleTile = tileAt(Position(col, row))

  def setHiddenTile(newTile: PuzzleTile) {
    _hiddenTile match {
      case Some(currentTile) if newTile != currentTile => {
        currentTile.visibilityChanged(toVisible = true)
        _hiddenTile = Some(newTile)
        newTile.visibilityChanged(toVisible = false)
      }
      case None => {
        _hiddenTile = Some(newTile)
        newTile.visibilityChanged(toVisible = false)
      }
      case _ => {}
    }
  }

  def setHiddenTileAt(col: Int, row: Int): Unit = setHiddenTileAt(Position(col, row))

  def setHiddenTileAt(position: Position) {
    val newTile = tileAt(position)
    setHiddenTile(newTile)
  }

  def clearHiddenTile() {
    _hiddenTile.map(_.visibilityChanged(toVisible = true))
    _hiddenTile = None
  }

  def hasHiddenTile = _hiddenTile.isDefined

  def hiddenTile: PuzzleTile = _hiddenTile.getOrElse(throw new java.util.NoSuchElementException("Does not have a hidden tile"))

}
