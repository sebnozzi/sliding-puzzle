package com.sebnozzi.slidingpuzzle.model

import com.sebnozzi.slidingpuzzle.model.structs.{GridSize, Position, Rect}

class Puzzle(gridSize: GridSize) {

  private var _movesDone = 0
  private var _solvedCallback: Option[() => Unit] = None
  private var _movesCountCallback: Option[() => Unit] = None
  private var _hiddenTile: Option[PuzzleTile] = None

  val columns: Int = gridSize.columns
  val rows: Int = gridSize.rows

  val tiles: List[PuzzleTile] = {
    (for (
      rowNr <- 1 to rows;
      colNr <- 1 to columns
    ) yield new PuzzleTile(puzzle = this, initialPosition = Position(colNr, rowNr))).toList
  }

  val positionsRect: Rect = Rect(Position(1, 1), Position(columns, rows))

  private def makeRandomMove(times: Int = 1): Unit = {
    if (hasHiddenTile) {
      for (_ <- 1 to times) {
        val tileToMove = hiddenTile.randomAdjacentTile
        tileToMove.moveToEmptySlot(shuffling = true)
      }
    }
  }

  def shuffle(): Unit = {
    makeRandomMove(times = 300)
    movesDone = 0
  }

  def movesDone: Int = _movesDone

  private def movesDone_=(newCount: Int): Unit = {
    _movesDone = newCount
    if (_movesCountCallback.isDefined)
      _movesCountCallback.get()
  }

  def canMoveToEmptySlot(tile: PuzzleTile): Boolean = {
    if (hasHiddenTile) {
      tile.isAdjacentTo(hiddenTile)
    } else {
      false
    }
  }

  def moveToEmptySlot(tile: PuzzleTile, shuffling: Boolean = false): Boolean = {
    if (canMoveToEmptySlot(tile)) {
      tile.swapPositionWith(hiddenTile, shuffling)
      if (!shuffling)
        didMoveToEmptySlot(tile)
      true
    } else {
      false
    }
  }

  def didMoveToEmptySlot(tile: PuzzleTile): Unit = {
    movesDone = movesDone + 1
    if (this.isSolved && _solvedCallback.isDefined)
      _solvedCallback.get()
  }

  def onSolved(callback: => Unit): Unit = {
    _solvedCallback = Some(() => callback)
  }

  def onMovesCountChange(callback: => Unit): Unit = {
    _movesCountCallback = Some(() => callback)
  }

  def reset(): Unit = {
    tiles.foreach(_.moveToInitialPosition())
    movesDone = 0
    clearHiddenTile()
  }

  def isSolved: Boolean = {
    tiles.forall { tile => tile.isAtInitialPosition }
  }

  def tileAt(position: Position): PuzzleTile = {
    tiles.find(tile => tile.currentPosition == position).get
  }

  def tileAt(col: Int, row: Int): PuzzleTile = tileAt(Position(col, row))

  def setHiddenTile(newTile: PuzzleTile): Unit = {
    _hiddenTile match {
      case Some(currentTile) if newTile != currentTile =>
        currentTile.visibilityChanged(toVisible = true)
        _hiddenTile = Some(newTile)
        newTile.visibilityChanged(toVisible = false)
      case None =>
        _hiddenTile = Some(newTile)
        newTile.visibilityChanged(toVisible = false)
      case _ => // Do nothing
    }
  }

  def setHiddenTileAt(col: Int, row: Int): Unit = setHiddenTileAt(Position(col, row))

  def setHiddenTileAt(position: Position): Unit = {
    val newTile = tileAt(position)
    setHiddenTile(newTile)
  }

  def clearHiddenTile(): Unit = {
    _hiddenTile.foreach(_.visibilityChanged(toVisible = true))
    _hiddenTile = None
  }

  def hasHiddenTile: Boolean = _hiddenTile.isDefined

  def hiddenTile: PuzzleTile = _hiddenTile.getOrElse(throw new java.util.NoSuchElementException("Does not have a hidden tile"))

}
