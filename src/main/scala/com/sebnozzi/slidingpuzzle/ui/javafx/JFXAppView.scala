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
import com.sebnozzi.slidingpuzzle.ui.AppView
import com.sebnozzi.slidingpuzzle.ui.PuzzleView

class JFXAppView(window: Stage) extends AppView {

  private val tilesBoardContainer = new Group
  private var _controlPanel: ControlPanel =
    _

  init()

  private def init() {
    val mainGroup = new VBox()
    _controlPanel = new ControlPanel()

    mainGroup.getChildren().add(_controlPanel)
    mainGroup.getChildren().add(tilesBoardContainer)

    _controlPanel.onResetPressed {
      this.resetClicked()
    }

    _controlPanel.onShufflePressed {
      this.shuffleClicked()
    }

    _controlPanel.onSizeChange { newSize =>
      this.newSizeSelected(newSize)
    }

    setupWithGroup(mainGroup)
  }

  def setMovesCount(newCount: Int) {
    _controlPanel.setMovesCount(newCount)
  }

  def selectGridSize(newSize: GridSize) {
    _controlPanel.selectGridSize(newSize)
  }

  def setPuzzleView(puzzleView: PuzzleView) {
    puzzleView match {
      case jfxView: JFXPuzzleView => setPuzzleView(jfxView)
    }
  }

  def setPuzzleView(puzzleView: JFXPuzzleView) {
    val grpChildren = tilesBoardContainer.getChildren()
    grpChildren.clear()
    grpChildren.add(puzzleView)
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
