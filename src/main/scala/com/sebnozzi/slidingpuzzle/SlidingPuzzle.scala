package com.sebnozzi.slidingpuzzle

import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.Group
import javafx.scene.image.Image
import javafx.scene.image.ImageView

object SlidingPuzzle extends App {
  Application.launch(classOf[SlidingPuzzle], "")
}

class SlidingPuzzle extends Application {

  lazy val imageView = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    val img = new Image(inputStream)
    new ImageView(img)
  }

  override def start(primaryStage: Stage) {
    val group = new Group();
    val scene = new Scene(group);
    group.getChildren().add(imageView)
    primaryStage.setTitle("Sliding Puzzle")
    primaryStage.setX(100)
    primaryStage.setY(100)
    primaryStage.setMinWidth(840)
    primaryStage.setMinHeight(560)
    primaryStage.setResizable(false)
    primaryStage.setFullScreen(false)
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
