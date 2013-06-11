package com.sebnozzi.slidingpuzzle.javafx

import _root_.javafx.application.Application
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.Group
import javafx.scene.image.Image
import javafx.scene.canvas.Canvas
import javafx.event.EventHandler
import javafx.stage.WindowEvent
import javafx.application.Platform
import javafx.scene.layout.HBox
import javafx.scene.Parent
import javafx.scene.layout.VBox
import javafx.scene.control.Button
import javafx.geometry.Insets
import javafx.geometry.Pos

class SlidingPuzzleJFXApp extends Application {

  val columns = 4
  val rows = 3
  
  private val shuffleButton = new Button("Shuffle")
  private val resetButton = new Button("Reset")

  val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  override def start(mainWindow: Stage) {
    val uiGroup = new HBox()
    val buttonsGroup = new VBox()
    
    List(
      shuffleButton,
      resetButton).foreach { button =>
        button.setMaxWidth(Double.MaxValue)
        buttonsGroup.getChildren().add(button)
      }
    
    buttonsGroup.setSpacing(20.0)
    buttonsGroup.setPadding(new Insets(20.0))
    buttonsGroup.setAlignment(Pos.BOTTOM_CENTER)
    buttonsGroup.setStyle("-fx-background-color: gray;")

    uiGroup.getChildren().add(slicesGroup())
    uiGroup.getChildren().add(buttonsGroup)

    setupWindow(mainWindow, uiGroup)
  }

  private def setupWindow(mainWindow: Stage, mainGroup: Parent) {
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

  private def slicesGroup() = {
    val group = new Group();
    sliceNodes(img).foreach { group.getChildren().add(_) }
    group
  }

  private def sliceNodes(img: Image): List[Canvas] = {
    def drawBorders(slice: Canvas) {
      val gc = slice.getGraphicsContext2D()
      gc.beginPath()
      gc.rect(0, 0, slice.getWidth(), slice.getHeight())
      gc.closePath()
      gc.stroke()
    }
    val slicer = new Slicer(img, xAmount = columns, yAmount = rows)
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
}
