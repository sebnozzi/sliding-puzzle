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
import javafx.scene.input.KeyEvent
import javafx.scene.Group
import com.sebnozzi.slidingpuzzle.model.GridSize

class GameWindowWrapper(window: Stage) {

  private val tilesBoardContainer = new Group
  private var _controlPanel: ControlPanel = _

  def controlPanel = { _controlPanel }

  init()

  private def init() {
    val mainGroup = new VBox()
    _controlPanel = new ControlPanel()

    mainGroup.getChildren().add(_controlPanel)
    mainGroup.getChildren().add(tilesBoardContainer)

    setupWithGroup(mainGroup)
  }
  
  def selectGridSize(gridSize:GridSize){
    _controlPanel.selectGridSize(gridSize)
  }

  def setTilesBoard(tilesBoard: TilesBoard) {
    val grpChildren = tilesBoardContainer.getChildren()
    grpChildren.clear()
    grpChildren.add(tilesBoard)
  }

  def onKeyPressed(callback: (KeyEvent) => Unit) {
    window.getScene().setOnKeyPressed(new EventHandler[KeyEvent]() {
      def handle(event: KeyEvent) {
        callback(event)
      }
    })
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
