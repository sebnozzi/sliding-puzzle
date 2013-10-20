package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

trait PuzzleView {

  def requestFocus() {}

  val tileViews: List[TileView]

}