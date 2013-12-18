package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

import scala.scalajs.js
import js.annotation.JSName

class JsAppView() extends AppView {

  val jsController = window.jsUIController
  
  override def setPuzzleView(puzzleView: PuzzleView) {
    // do nothing
  }

  override def setMovesCount(newCount: Int) {
    jsController.setMovesCount(newCount)
  }
  
  override def selectGridSize(newSize: GridSize) {
    jsController.onSizeChanged()
  }

  override def show() {
    // nothing for now
  }

}