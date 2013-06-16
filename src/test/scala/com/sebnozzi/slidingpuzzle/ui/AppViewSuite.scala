package com.sebnozzi.slidingpuzzle.ui

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class AppViewSuite extends FunSuite with BeforeAndAfter {
  
  var appView:AppView = _

  before{
    appView = new AppView() {}
  }
  
  test("handles shuffle click") {
    var called = false
    appView.onShuffleClicked {
      called = true
    }
    appView.shuffleClicked()
    assert(called, "was not called")
  }
  
  test("handles shuffle click when no callback") {
    appView.shuffleClicked()
  } 
  
  test("handles reset click") {
    var called = false
    appView.onResetClicked {
      called = true
    }
    appView.resetClicked()
    assert(called, "was not called")
  }
  
  test("handles reset click when no callback") {
    appView.resetClicked()
  } 
  

}