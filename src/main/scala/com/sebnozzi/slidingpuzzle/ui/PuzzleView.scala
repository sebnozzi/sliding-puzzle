package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.GridSize

trait PuzzleView {

  def requestFocus() {}

  val tileViews: List[TileView]

}