package com.sebnozzi.slidingpuzzle.javafx

import _root_.javafx.application.Application
import javafx.stage.Stage
import javafx.scene.image.ImageView
import javafx.scene.Scene
import javafx.scene.Group
import javafx.scene.image.Image
import javafx.scene.canvas.Canvas
import javafx.event.EventHandler
import javafx.stage.WindowEvent
import javafx.application.Platform

class SlidingPuzzleJFXApp extends Application {

  val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  lazy val imageView = {
    new ImageView(img)
  }

  override def start(mainWindow: Stage) {
    val mainNodeGroup = {
      val slicesGroup = new Group();
      val sliceNodes = {
        def drawBorders(slice: Canvas) {
          val gc = slice.getGraphicsContext2D()
          gc.beginPath()
          gc.rect(0, 0, slice.getWidth(), slice.getHeight())
          gc.closePath()
          gc.stroke()
        }
        val slicer = new Slicer(img, xAmount = 4, yAmount = 3)
        slicer.slicePositions.map {
          case (x, y) =>
            val xCoord = (x - 1) * slicer.sliceWidth
            val yCoord = (y - 1) * slicer.sliceHeight
            val slice = slicer.sliceAt(x, y)
            slice.setLayoutX(xCoord)
            slice.setLayoutY(yCoord)
            drawBorders(slice)
            slice
        }
      }
      sliceNodes.foreach { slicesGroup.getChildren().add(_) }
      slicesGroup
    }

    setupWindow(mainWindow, mainNodeGroup)
  }

  private def setupWindow(mainWindow: Stage, mainGroup: Group) {
    val scene = new Scene(mainGroup)
    mainWindow.setScene(scene)

    mainWindow.setTitle("Sliding Puzzle")
    mainWindow.setX(100)
    mainWindow.setY(100)
    mainWindow.setMinWidth(840)
    mainWindow.setMinHeight(560)
    mainWindow.setResizable(false)
    mainWindow.setFullScreen(false)
    mainWindow.show()

    mainWindow.setOnCloseRequest(new EventHandler[WindowEvent] {
      def handle(event: WindowEvent) {
        Platform.exit()
      }
    })
  }
}
