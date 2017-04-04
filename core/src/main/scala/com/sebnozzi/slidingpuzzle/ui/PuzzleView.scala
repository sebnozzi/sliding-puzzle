package com.sebnozzi.slidingpuzzle.ui

trait PuzzleView {
  def requestFocus() {}
  def tileViews: List[TileView]
}
