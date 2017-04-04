package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.Position
import org.scalatest.FunSuite

class TileViewSuite extends FunSuite {

  val view = new TileView() {
    def makeVisible(animate: Boolean) {}
    def makeHidden() {}
    def moveTileTo(pos: Position, animate: Boolean = false) {}
  }

  test("on mouse pressed callback") {
    var called = false
    view.onMousePressed {
      called = true
    }
    view.mousePressed()
    assert(called)
  }

}
