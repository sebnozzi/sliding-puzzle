package com.sebnozzi.slidingpuzzle

import javafx.application.Application
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.Group
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.event.EventHandler
import javafx.stage.WindowEvent
import javafx.application.Platform

object SlidingPuzzle extends App {
  Application.launch(classOf[SlidingPuzzle], "")
}

class SlidingPuzzle extends Application {

  val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  lazy val imageView = {
    new ImageView(img)
  }

  override def start(primaryStage: Stage) {
    val group = {
      val group = new Group();
      val nodesToAdd = {
        val slicer = new Slicer(img, xAmount = 4, yAmount = 2)
        slicer.slicePositions.map {
          case (x, y) =>
            val xCoord = (x - 1) * slicer.sliceWidth
            val yCoord = (y - 1) * slicer.sliceHeight
            val slice = slicer.sliceAt(x, y)
            slice.setLayoutX(xCoord)
            slice.setLayoutY(yCoord)
            slice
        }
      }
      nodesToAdd.foreach { group.getChildren().add(_) }
      group
    }

    setupStage(primaryStage, group)
  }

  private def setupStage(stage: Stage, mainGroup: Group) {
    val scene = new Scene(mainGroup)
    stage.setScene(scene)

    stage.setTitle("Sliding Puzzle")
    stage.setX(100)
    stage.setY(100)
    stage.setMinWidth(840)
    stage.setMinHeight(560)
    stage.setResizable(false)
    stage.setFullScreen(false)
    stage.show()

    stage.setOnCloseRequest(new EventHandler[WindowEvent] {
      def handle(event: WindowEvent) {
        Platform.exit()
      }
    })
  }
}
