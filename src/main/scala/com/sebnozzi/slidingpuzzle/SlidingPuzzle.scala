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
    primaryStage.setScene(scene)

    group.getChildren().add(imageView)

    setupStage(primaryStage)
  }

  private def setupStage(stage: Stage) {
    stage.setTitle("Sliding Puzzle")
    stage.setX(100)
    stage.setY(100)
    stage.setMinWidth(840)
    stage.setMinHeight(560)
    stage.setResizable(false)
    stage.setFullScreen(false)
    stage.show()
  }
}
