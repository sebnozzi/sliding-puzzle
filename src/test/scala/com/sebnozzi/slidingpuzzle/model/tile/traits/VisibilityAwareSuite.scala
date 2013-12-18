package com.sebnozzi.slidingpuzzle.model.tile.traits

import org.scalatest.FunSuite

class VisibilityAwareSuite extends FunSuite {

  test("callback when visibility changes") {
    var calls = 0
    val tile = new VisibilityAware() {}
    tile.onVisibilityChange { toVisible =>
      calls += 1
    }
    tile.visibilityChanged(toVisible=true)
    tile.visibilityChanged(toVisible=false)
    assert(calls === 2)
  }

}