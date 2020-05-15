package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.Position
import org.scalatest.funsuite.AnyFunSuite

class TileViewSuite extends AnyFunSuite {

  val view: TileView = new TileView() {
    def makeVisible(animate: Boolean): Unit = {}
    def makeHidden(): Unit = {}
    def moveTileTo(pos: Position, animate: Boolean = false): Unit = {}
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
