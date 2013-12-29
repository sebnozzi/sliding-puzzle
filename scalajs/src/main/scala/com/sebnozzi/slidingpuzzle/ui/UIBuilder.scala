package com.sebnozzi.slidingpuzzle.ui

import org.scalajs.jquery._
import org.scalajs.dom.HTMLCanvasElement

trait UIBuilder extends HtmlBuilder with JqToolbar {

  def availableSizes: Seq[(Int, Int)]

  val scalaJsLogo = "resources/img/scala-js-logo-16.png"

  val puzzleDiv = div(cssClass="puzzle")
  val shuffleButton = button(id = "shuffleButton", label = "Shuffle!")
  val resetButton = button(id = "resetButton", label = "Reset")
  val sizeSelect: JQuery = {
    val selectNode = select(id = "sizeSelector")
    for ((cols, rows) <- availableSizes) {
      selectNode.append(option(value = s"$cols,$rows",
        label = s"${cols}x${rows}"))
    }
    selectNode
  }
  val (movesWidget, movesCount) = labelledValue(id = "movesCount", label = "Moves:", value = "0")

  def buildUI(target: JQuery) {
    jQuery(target).append(gameDiv)
    jQuery(target).append(scalaJsCreditsDiv)
  }

  private def gameDiv = {
    val gameDiv = div(cssClass = "game")
    gameDiv.append(buildToolbar)
    gameDiv.append(puzzleDiv)
    gameDiv
  }

  private def scalaJsCreditsDiv = {
    val creditsDiv = div(cssClass = "credits")
    creditsDiv.html(s"""Powered by <img src="$scalaJsLogo" 
        valign="middle"/><a href="http://www.scala-js.org/">Scala.js</a>""")
    creditsDiv
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