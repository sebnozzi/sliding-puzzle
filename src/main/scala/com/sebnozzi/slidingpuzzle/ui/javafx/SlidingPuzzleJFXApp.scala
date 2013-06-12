package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.application.Application
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import javafx.stage.Stage
import com.sebnozzi.slidingpuzzle.model.Position
import javafx.scene.canvas.Canvas
import javafx.scene.layout.VBox
import com.sebnozzi.slidingpuzzle.model.Game

class SlidingPuzzleJFXApp extends Application {

  val columns = 4
  val rows = 3
  val game = new Game(columns, rows)

  val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  override def start(mainWindow: Stage) {
    val gameWindow = new GameWindowWrapper(mainWindow, img, columns, rows)
    
    val controlPanel = gameWindow.controlPanel
    val tilesBoard = gameWindow.tilesBoard
    
    controlPanel.setMovesCount(game.movesDone)

    mainWindow.show()
  }

}
