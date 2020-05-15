package com.sebnozzi.slidingpuzzle.ui

trait PuzzleView {
  def requestFocus(): Unit = {}
  def tileViews: Seq[TileView]
}
