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
import javafx.event.ActionEvent

class SlidingPuzzleJFXApp extends Application {

  val columns = 4
  val rows = 3

  val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  override def start(mainWindow: Stage) {
    val uiGroup = new HBox()
    val buttonsPanel = new ButtonsPanel()
    val tilesBoard = new TilesBoard(img, columns, rows)
    
    buttonsPanel.onResetPressed(resetPressed)
    buttonsPanel.onShufflePressed(shufflePressed)

    uiGroup.getChildren().add(tilesBoard)
    uiGroup.getChildren().add(buttonsPanel)

    val mainWindow = new MainWindow(uiGroup)
    mainWindow.show()
  }

  private def shufflePressed() {
    println("Shuffle pressed")
  }

  private def resetPressed() {
    println("Reset pressed")
  }


}
