package com.sebnozzi.slidingpuzzle

class Game(columns: Int, rows: Int) {

  private var _hiddenTile: Option[Tile] = None

  val tiles: List[Tile] = {
    (for (
      rowNr <- 1 to rows;
      colNr <- 1 to columns
    ) yield new Tile(game = this, initialPosition = Position(colNr, rowNr))).toList
  }

  val isSolved = true

  def tileAt(col: Int, row: Int) = tiles((col - 1) + (row - 1) * columns)

  def setHiddenTileAt(col: Int, row: Int) { _hiddenTile = Some(tileAt(col, row)) }

  def hiddenTile: Tile = _hiddenTile.get

}