package com.sebnozzi.slidingpuzzle

import _root_.javafx.stage.Stage
import _root_.javafx.application.Application

import com.sebnozzi.slidingpuzzle.ui.javafx.JFXAppController

object SlidingPuzzleApp extends App {
  import Application._
  launch(classOf[SlidingPuzzleJFXApp], args:_*)
}

class SlidingPuzzleJFXApp extends Application {
  override def start(mainWindow: Stage) {
    val appController = new JFXAppController(mainWindow)
    appController.start()
  }
}