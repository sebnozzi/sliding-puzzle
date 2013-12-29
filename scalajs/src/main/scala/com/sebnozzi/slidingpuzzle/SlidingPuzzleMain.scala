package com.sebnozzi.slidingpuzzle

import com.sebnozzi.slidingpuzzle.ui.AppController
import com.sebnozzi.slidingpuzzle.ui.JsAppController

import org.scalajs.jquery._

object SlidingPuzzleMain {
  def main(jqueryImages: JQuery): Unit = {
    for (i <- 0 until jqueryImages.length.toInt) {
      val jqueryImg = jQuery(jqueryImages.get(i))
      val appController = new JsAppController(jqueryImg)
      appController.start()
    }
  }
}
