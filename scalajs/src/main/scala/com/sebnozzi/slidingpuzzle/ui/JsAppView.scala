package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import scala.scalajs.js
import js.annotation.JSName
import com.sebnozzi.slidingpuzzle.model.structs.GridSize

class JsAppView() extends AppView with HasJsController {
  
  jsController.onShufflePressed(() => {
    shuffleClicked()
  })
  
  jsController.onResetPressed(() => {
    resetClicked()
  })
  
  jsController.onSizeChanged((colsJs:js.Number, rowsJs:js.Number) => {
    val cols = colsJs.intValue
    val rows = rowsJs.intValue
    val newSize = GridSize(cols, rows)
    log("newSize: " + newSize.toString)
    selectGridSize(newSize)
    js.Object
  })
  
  override def setPuzzleView(puzzleView: PuzzleView) {
    // do nothing
  }
  
  override def setMovesCount(newCount: Int) {
    jsController.setMovesCount(newCount)
  }
  
  override def selectGridSize(newSize: GridSize) {
    jsController.setupGrid(newSize.columns, newSize.rows)
  }

  override def show() {
    // nothing for now
  }

}