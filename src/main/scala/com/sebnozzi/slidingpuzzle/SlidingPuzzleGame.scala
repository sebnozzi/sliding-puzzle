package com.sebnozzi.slidingpuzzle

class SlidingPuzzleGame(columns:Int, rows:Int) {

  private var _hiddenTile:Option[Tile] = None
  
  val tiles = (1 to (columns * rows)).map{ _ => new Tile() }
  
  val isSolved = true
  
  def tileAt(x:Int, y:Int) = tiles((x-1) + (y-1)*columns)
  
  def setHiddenTileAt(x:Int, y:Int) {_hiddenTile = Some(tileAt(x,y)) }
  
  def hiddenTile:Tile = _hiddenTile.get
  
}