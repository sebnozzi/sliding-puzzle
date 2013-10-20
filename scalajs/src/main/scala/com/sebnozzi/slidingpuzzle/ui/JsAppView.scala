package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import scala.js.annotation.JSName

class JsAppView(container: JQuery) extends AppView {
  
  val resetButton = JQuery("<button>reset</button>")
  val shuffleButton = JQuery("<button>shuffle</button>")
  val gridSelect = JQuery("<select></select>")
  val movesLabel = JQuery("<span id=\"movesLabel\">Moves: 0</span>")

  val gridSizes = List(
    GridSize(3, 2),
    GridSize(3, 3),
    GridSize(4, 3),
    GridSize(6, 4))

  gridSizes.foreach { size =>
    val optLabel = size.columns +"x"+size.rows
    val opt = JQuery("<option>" + optLabel +"</option>")
    gridSelect.append(opt)
  }
  
  val toolbar = JQuery("<div id=\"toolbar\"/>")
  toolbar.append(gridSelect)
  toolbar.append(shuffleButton)
  toolbar.append(resetButton)
  toolbar.append(movesLabel)
  container.append(toolbar)

  override def setPuzzleView(puzzleView: PuzzleView) {
  }

  override def setMovesCount(newCount: Int) {}
  override def selectGridSize(newSize: GridSize) {}

  override def show() {}

}