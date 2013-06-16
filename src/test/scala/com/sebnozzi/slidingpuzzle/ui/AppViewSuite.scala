package com.sebnozzi.slidingpuzzle.ui

import org.scalatest.FunSuite

class AppViewSuite extends FunSuite {

  test("handles shuffle click") {
    var called = false
    val appView = new AppView() {
      
    }
    appView.onShuffleClicked {
      called = true
    }
    appView.shuffleClicked()
    assert(called, "was not called")
  }

}