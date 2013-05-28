package com.sebnozzi.slidingpuzzle

import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.Group


object SlidingPuzzle extends App {
  Application.launch(classOf[SlidingPuzzle], "")
}

class SlidingPuzzle extends Application {
  override def start(primaryStage: Stage) {
    val group = new Group();
    val scene = new Scene(group);
    primaryStage.setTitle("Sliding Puzzle")
    primaryStage.setX(100)
    primaryStage.setY(100)
    primaryStage.setMinWidth(400)
    primaryStage.setMinHeight(300)
    primaryStage.setResizable(false)
    primaryStage.setFullScreen(false)
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
