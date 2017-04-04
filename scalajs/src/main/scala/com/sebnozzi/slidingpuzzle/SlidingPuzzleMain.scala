package com.sebnozzi.slidingpuzzle

import com.sebnozzi.slidingpuzzle.ui.{AppController, JsAppController}
import org.scalajs.jquery._

import scala.scalajs.js.annotation._

@JSExportTopLevel("SlidingPuzzleMain")
object SlidingPuzzleMain {

  @JSExport
  def main(jqueryImages: JQuery): Unit = {
    for (i <- 0 until jqueryImages.length.toInt) {
      val jqueryImg = jQuery(jqueryImages.get(i))
      val appController:AppController = new JsAppController(jqueryImg)
      appController.start()
    }
  }

}
