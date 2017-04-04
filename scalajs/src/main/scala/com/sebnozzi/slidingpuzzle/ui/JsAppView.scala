package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize


class JsAppView(toolbar: JqToolbar) extends AppView {

  setupCallbacksOnUI()

  private def setupCallbacksOnUI() {
    toolbar.shuffleButton.click { () =>
      shuffleClicked()
    }
    toolbar.resetButton.click { () =>
      resetClicked()
    }
    toolbar.sizeSelect.change { () =>
      val newSize = getSelectedSize()
      newSizeSelected(newSize)
    }
  }

  private def getSelectedSize(): GridSize = {
    val commaSeparatedNumbers: String = toolbar.sizeSelect.value().asInstanceOf[String]
    val parts = commaSeparatedNumbers.split(",")
    val cols = parts(0).toInt
    val rows = parts(1).toInt
    GridSize(cols, rows)
  }

  // Called when the amount of moves changes within the model.
  // The UI needs to be updated to reflect this fact.
  override def setMovesCount(newCount: Int) {
    toolbar.movesCount.html(newCount.toString);
  }

  // Called when the grid-size changes from within the model.
  // The UI needs to be updated to reflect this fact.
  override def selectGridSize(newSize: GridSize) {
    val targetVal = newSize.columns + "," + newSize.rows
    toolbar.sizeSelect.value(targetVal);
  }

  override def setPuzzleView(puzzleView: PuzzleView) {
    // do nothing, view permanently set on creation
    // (should add here to DOM?)
  }

  override def show() {
    // do nothing, UI already showing on construction
  }

}
