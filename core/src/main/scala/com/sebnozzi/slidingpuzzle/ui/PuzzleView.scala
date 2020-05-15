package com.sebnozzi.slidingpuzzle.ui

trait PuzzleView {
  def requestFocus(): Unit = {}
  def tileViews: List[TileView]
}
