package com.sebnozzi.slidingpuzzle

import _root_.javafx.application.Application
import _root_.javafx.stage.Stage

import com.sebnozzi.slidingpuzzle.ui.javafx.JFXAppController

object SlidingPuzzleMain extends App {
  import Application._
  launch(classOf[SlidingPuzzleJFXApp], args:_*)
}

class SlidingPuzzleJFXApp extends Application {
  override def start(mainWindow: Stage) {
    val appController = new JFXAppController(mainWindow)
    appController.start()
  }
}
