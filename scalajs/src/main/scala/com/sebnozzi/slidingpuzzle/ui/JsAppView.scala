package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import scala.scalajs.js
import org.scalajs.jquery._

class JsAppView() extends AppView with HasJsController {
  
  setupCallbacksOnUI()

  private def setupCallbacksOnUI() {
    log("Setting up shuffle button callback")
    jQuery("#shuffleButton").click { () =>
      log("Shuffle button clicked")
      shuffleClicked()
    }
    jQuery("#resetButton").click { () =>
      resetClicked()
    }
    jQuery("#sizeSelector").change { () =>
      val newSize = getSelectedSize()
      newSizeSelected(newSize)
    }
  }

  private def getSelectedSize(): GridSize = {
    val commaSeparatedNumbers: String = jQuery("#sizeSelector").value().asInstanceOf[js.String]
    val parts = commaSeparatedNumbers.split(",")
    val cols = parts(0).toInt
    val rows = parts(1).toInt
    GridSize(cols, rows)
  }

  // Called when the amount of moves changes within the model.
  // The UI needs to be updated to reflect this fact.
  override def setMovesCount(newCount: Int) {
    jQuery("#movesCount").html(newCount.toString);
  }

  // Called when the grid-size changes from within the model.
  // The UI needs to be updated to reflect this fact.
  override def selectGridSize(newSize: GridSize) {
    val targetVal = newSize.columns + "," + newSize.rows
    jQuery("#sizeSelector").value(targetVal);
  }

  override def setPuzzleView(puzzleView: PuzzleView) {
    // do nothing, view permanently set on creation
  }

  override def show() {
    // do nothing, UI already showing on construction
  }

}