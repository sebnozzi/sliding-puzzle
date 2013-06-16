package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.GridSize

class TestTileViewSource(val gridSize:GridSize) extends TileViewSource {

  def tileViews:List[TileView]= {
    ((1 to (gridSize.columns * gridSize.rows)).map{ _ => new TestTileView() }).toList
  }
  
  
}