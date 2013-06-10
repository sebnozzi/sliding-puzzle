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

  val isSolved = true

  def tileAt(position:Position) = tiles((position.col - 1) + (position.row - 1) * columns)

  def setHiddenTileAt(position: Position) { _hiddenTile = Some(tileAt(position)) }

  def hasHiddenTile = _hiddenTile.isDefined
  
  def hiddenTile: Tile = _hiddenTile.getOrElse(throw new java.util.NoSuchElementException("Does not have a hidden tile"))

}