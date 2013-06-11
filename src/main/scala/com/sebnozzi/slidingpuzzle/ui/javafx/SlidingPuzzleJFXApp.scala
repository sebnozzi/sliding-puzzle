package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.application.Application
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import javafx.stage.Stage
import Implicits._
import com.sebnozzi.slidingpuzzle.model.Position
import javafx.scene.canvas.Canvas

class SlidingPuzzleJFXApp extends Application {

  val columns = 4
  val rows = 3

  val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }
  
  val tilesBoard = new TilesBoard(img, columns, rows)

  override def start(mainWindow: Stage) {
    val mainGroup = new HBox()
    val buttonsPanel = new ButtonsPanel()
 
    buttonsPanel.onResetPressed(resetPressed)
    buttonsPanel.onShufflePressed(shufflePressed)
    tilesBoard.onTilePressed(tilePressed)

    mainGroup.getChildren().add(tilesBoard)
    mainGroup.getChildren().add(buttonsPanel)
    
    mainWindow.setupWithGroup(mainGroup)
    mainWindow.show()
  }

  private def tilePressed(tile:Canvas) {
    tilesBoard.moveTile(tile, destination = Position(1, 1))
  }

  private def shufflePressed() {
    println("Shuffle pressed")
  }

  private def resetPressed() {
    println("Reset pressed")
  }

}
