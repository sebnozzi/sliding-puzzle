package com.sebnozzi.slidingpuzzle

//import js.Dynamic.{ global => g }
import com.sebnozzi.slidingpuzzle.ui.AppController
import com.sebnozzi.slidingpuzzle.ui.JsAppController

object SlidingPuzzleMain {
  def main(puzzleImgId:String): Unit = {
    val appController = new JsAppController(puzzleImgId)
    appController.start()
  }
}
