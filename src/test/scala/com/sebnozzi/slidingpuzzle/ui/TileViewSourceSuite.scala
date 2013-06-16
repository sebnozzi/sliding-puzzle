package com.sebnozzi.slidingpuzzle.ui

import org.scalatest.FunSuite
import com.sebnozzi.slidingpuzzle.model.GridSize

class TileViewSourceSuite extends FunSuite {

  test("getting tiles"){
    val tileViewSource = new TestTileViewSource(GridSize(4,3))
    tileViewSource.tileViews
  }
  
}