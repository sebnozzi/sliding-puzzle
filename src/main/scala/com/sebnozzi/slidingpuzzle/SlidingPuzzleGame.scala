package com.sebnozzi.slidingpuzzle

class SlidingPuzzleGame(columns: Int, rows: Int) {

  private var _hiddenTile: Option[Tile] = None

  val tiles: List[Tile] = {
    (for (
      rowNr <- 1 to rows;
      colNr <- 1 to columns
    ) yield new Tile(Position(colNr, rowNr))).toList
  }

  val isSolved = true

  def tileAt(x: Int, y: Int) = tiles((x - 1) + (y - 1) * columns)

  def setHiddenTileAt(x: Int, y: Int) { _hiddenTile = Some(tileAt(x, y)) }

  def hiddenTile: Tile = _hiddenTile.get

}