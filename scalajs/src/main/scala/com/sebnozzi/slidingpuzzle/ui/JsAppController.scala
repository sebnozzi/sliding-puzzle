package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import org.scalajs.dom.html
import org.scalajs.jquery._

import scala.scalajs.js

class JsAppController(jqueryImg: JQuery) extends AppController
  with UIBuilder with JqToolbar {

  lazy val availableSizes = Seq(
    (3, 2),
    (3, 3),
    (4, 3),
    (6, 4))

  val srcImg: html.Image = jqueryImg.asInstanceOf[js.Array[html.Image]](0)
  val target: JQuery = jQuery(srcImg).parent()

  override def start(): Unit = {
    buildUI(target)
    super.start()
    jQuery(srcImg).hide()
  }

  override def createAppView(): AppView = {
    new JsAppView(this)
  }

  override def createPuzzleView(gridSize: GridSize): PuzzleView = {
    new JsPuzzleView(srcImg, puzzleDiv, gridSize)
  }

}
