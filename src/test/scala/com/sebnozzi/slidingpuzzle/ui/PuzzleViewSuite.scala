package com.sebnozzi.slidingpuzzle.ui

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import com.sebnozzi.slidingpuzzle.model.GridSize
import com.sebnozzi.slidingpuzzle.model.Position
import com.sebnozzi.slidingpuzzle.model.GridSize

class PuzzleViewSuite extends FunSuite with BeforeAndAfter {

  var view: PuzzleView = _

  before {
    view = new PuzzleView() {
      val tileViews:List[TileView] = {
        List(new TestTileView, new TestTileView, new TestTileView)
      }
      //
    }
  }

  test("requests focus") {
    view.requestFocus()
  }

  test("getting tile views") {
    val tiles: List[TileView] = view.tileViews
  }

  test("amount of tiles") {
    val tiles: List[TileView] = view.tileViews
    assert(tiles.size === 9)
  }

}