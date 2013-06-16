package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.GridSize

trait TileViewSource {

  val gridSize:GridSize
  
  def tileViews:List[TileView]
  
}