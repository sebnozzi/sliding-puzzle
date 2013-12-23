package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import scala.scalajs.js
import js.annotation.JSName
import com.sebnozzi.slidingpuzzle.model.structs.GridSize

class JsAppView() extends AppView with HasJsController {

  setupCallbacksOnUI();

  private def setupCallbacksOnUI() {
    jsController.onShufflePressed(() => {
      shuffleClicked()
    })
    jsController.onResetPressed(() => {
      resetClicked()
    })
    jsController.onSizeChanged((colsJs: js.Number, rowsJs: js.Number) => {
      val cols = colsJs.intValue
      val rows = rowsJs.intValue
      val newSize = GridSize(cols, rows)
      newSizeSelected(newSize)
      js.Object
    })
  }

  // Called when the amount of moves changes within the model.
  // The UI needs to be updated to reflect this fact.
  override def setMovesCount(newCount: Int) {
    jsController.setMovesCount(newCount)
  }

  // Called when the grid-size changes from within the model.
  // The UI needs to be updated to reflect this fact.
  override def selectGridSize(newSize: GridSize) {
    jsController.setSelectedSize(newSize.columns, newSize.rows)
  }

  override def setPuzzleView(puzzleView: PuzzleView) {
    // do nothing, view permanently set on creation
  }

  override def show() {
    // do nothing, UI already showing on construction
  }

}