package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.scene.Parent
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.event.EventHandler
import javafx.stage.WindowEvent
import javafx.application.Platform
import javafx.scene.paint.Paint
import javafx.scene.paint.Color
import javafx.scene.layout.VBox
import javafx.scene.image.Image

class GameWindowWrapper(window: Stage, img: Image, val columns: Int, val rows: Int) {

  private var _tilesBoard: TilesBoard = _
  private var _controlPanel: ControlPanel = _

  def tilesBoard = { _tilesBoard }
  def controlPanel = { _controlPanel }

  init(img, columns, rows)

  def init(img: Image, columns: Int, rows: Int) {
    _tilesBoard = new TilesBoard(img, columns, rows)

    val mainGroup = new VBox()
    _controlPanel = new ControlPanel()

    mainGroup.getChildren().add(_controlPanel)
    mainGroup.getChildren().add(_tilesBoard)

    setupWithGroup(mainGroup)
  }

  private def setupWithGroup(mainGroup: Parent) {
    val scene = new Scene(mainGroup)
    scene.setFill(Color.BLACK)
    window.setScene(scene)

    window.setTitle("Sliding Puzzle")
    window.setX(100)
    window.setY(100)
    window.setMinWidth(840)
    window.setMinHeight(560)
    window.setResizable(false)
    window.setFullScreen(false)

    window.setOnCloseRequest(new EventHandler[WindowEvent] {
      def handle(event: WindowEvent) {
        Platform.exit()
      }
    })
  }
}
