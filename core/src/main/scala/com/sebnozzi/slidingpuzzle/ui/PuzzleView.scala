package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

trait PuzzleView {
  def requestFocus() {}
  def tileViews: List[TileView]
}