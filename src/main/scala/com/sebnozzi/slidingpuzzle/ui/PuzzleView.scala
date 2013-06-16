package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.GridSize

class PuzzleView(size:GridSize) {

  def requestFocus(){}
  
  def tileViews:List[TileView] = {
    ((1 to (size.columns * size.rows)).map{ _ => new TileView}).toList
  }
  
}