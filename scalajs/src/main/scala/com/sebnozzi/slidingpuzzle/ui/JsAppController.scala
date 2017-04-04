package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

import scala.scalajs.js
import org.scalajs.jquery._
import org.scalajs.dom.html

class JsAppController(jqueryImg: JQuery) extends AppController
  with UIBuilder with JqToolbar {

  lazy val availableSizes = Seq(
    (3, 2),
    (3, 3),
    (4, 3),
    (6, 4))

  val srcImg: html.Image = jqueryImg.asInstanceOf[js.Array[html.Image]](0)
  val target = jQuery(srcImg).parent()

  override def start() {
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
