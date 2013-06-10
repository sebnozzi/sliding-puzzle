package com.sebnozzi.slidingpuzzle

class Game(val columns: Int, val rows: Int) {

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
  
  def resetState() {
    //tiles.foreach(_.backToInitialPosition)
    clearHiddenTile()
  }

  def isSolved = {
    tiles.forall { tile => tile.isAtInitialPosition }
  }

  def tileAt(position: Position) = tiles((position.col - 1) + (position.row - 1) * columns)

  def setHiddenTileAt(position: Position) { _hiddenTile = Some(tileAt(position)) }
  def clearHiddenTile() { _hiddenTile = None }

  def hasHiddenTile = _hiddenTile.isDefined

  def hiddenTile: Tile = _hiddenTile.getOrElse(throw new java.util.NoSuchElementException("Does not have a hidden tile"))

}