package com.sebnozzi.slidingpuzzle.model.tile.traits

import org.scalatest.funsuite.AnyFunSuite


class VisibilityAwareSuite extends AnyFunSuite {

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