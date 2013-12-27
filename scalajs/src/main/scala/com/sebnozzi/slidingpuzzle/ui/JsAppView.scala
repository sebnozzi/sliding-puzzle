package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import scala.scalajs.js
import org.scalajs.jquery._

class JsAppView() extends AppView with HasJsController {

  setupCallbacksOnUI()

  private def setupCallbacksOnUI() {
    log("Setting up shuffle button callback")
    jQuery("#shuffleButton").click{ () => 
      log("Shuffle button clicked")
      shuffleClicked()
    }
    jQuery("#resetButton").click { () => 
      resetClicked()
    }
    jQuery("#sizeSelector").change { () => 
      val jsSize = jsController.getSelectedSize()
      val cols = jsSize.cols.toInt
      val rows = jsSize.rows.toInt
      val newSize = GridSize(cols, rows)
      newSizeSelected(newSize)
    }
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