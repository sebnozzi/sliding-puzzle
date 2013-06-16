package com.sebnozzi.slidingpuzzle.ui

import org.scalatest.FunSuite

class PuzzleViewSuite extends FunSuite {

  test("requests focus"){
    val view = new PuzzleView(){}
    view.requestFocus()
  }
  
}