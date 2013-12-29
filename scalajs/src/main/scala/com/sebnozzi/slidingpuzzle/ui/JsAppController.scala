package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize

import scala.scalajs.js
import org.scalajs.jquery._
import org.scalajs.dom.HTMLImageElement

class JsAppController(imgId: String) extends AppController {

  val availableSizes = Seq(
    (3, 2),
    (3, 3),
    (4, 3),
    (6, 4))

  val srcImg: HTMLImageElement = jQuery(s"#$imgId").asInstanceOf[js.Array[HTMLImageElement]](0)
  val target = jQuery(srcImg).parent()
  
  override def start() {
    UIBuilder.buildUI(target, availableSizes)
    super.start()
    jQuery(srcImg).hide()
  }

  override def createAppView(): AppView = {
    new JsAppView()
  }

  override def createPuzzleView(gridSize: GridSize): PuzzleView = {
    new JsPuzzleView(srcImg, gridSize)
  }

}