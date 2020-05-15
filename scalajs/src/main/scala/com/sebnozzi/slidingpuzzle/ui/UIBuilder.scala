package com.sebnozzi.slidingpuzzle.ui

import org.scalajs.jquery._

trait UIBuilder extends HtmlBuilder with JqToolbar {

  def availableSizes: Seq[(Int, Int)]

  val puzzleDiv: JQuery = div(cssClass="puzzle")
  val shuffleButton: JQuery = button(label = "Shuffle!")
  val resetButton: JQuery = button(label = "Reset")
  val sizeSelect: JQuery = {
    val selectNode = select()
    for ((cols, rows) <- availableSizes) {
      selectNode.append(option(value = s"$cols,$rows",
        label = s"${cols}x$rows"))
    }
    selectNode
  }
  val (movesWidget, movesCount) = labelledValue(cssClass = "moves", label = "Moves:", value = "0")

  def buildUI(target: JQuery): Unit = {
    jQuery(target).append(gameDiv)
  }

  private def gameDiv: JQuery = {
    val gameDiv = div(cssClass = "game")
    gameDiv.append(buildToolbar)
    gameDiv.append(puzzleDiv)
    gameDiv
  }

  private def buildToolbar: JQuery = {
    val toolbar = div(cssClass = "toolbar")
    toolbar.append(sizeSelect)
    toolbar.append(shuffleButton)
    toolbar.append(resetButton)
    toolbar.append(movesWidget)
    toolbar
  }

}
