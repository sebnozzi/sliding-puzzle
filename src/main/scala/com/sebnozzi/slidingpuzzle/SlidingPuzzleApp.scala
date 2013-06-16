package com.sebnozzi.slidingpuzzle

import _root_.javafx.application.Application
import com.sebnozzi.slidingpuzzle.ui.javafx.JFXAppController
import javafx.stage.Stage

object SlidingPuzzleApp extends App {

  Application.launch(classOf[SlidingPuzzleJFXApp], "")

}

class SlidingPuzzleJFXApp extends Application {

  override def start(mainWindow: Stage) {
    val appController = new JFXAppController(mainWindow)
    appController.start()
  }
  
}