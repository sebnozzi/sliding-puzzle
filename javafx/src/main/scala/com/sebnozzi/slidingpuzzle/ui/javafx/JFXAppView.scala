package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.{Group, Parent, Scene}
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.{Stage, WindowEvent}

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.ui.{AppView, PuzzleView}
import com.sebnozzi.slidingpuzzle.ui.keys.{Down, Left, Right, Up}

class JFXAppView(window: Stage) extends AppView {

  private val tilesBoardContainer = new Group
  private var _controlPanel: ControlPanel = _

  init()

  private def init(): Unit = {
    val mainGroup = new VBox()
    _controlPanel = new ControlPanel()

    mainGroup.getChildren.add(_controlPanel)
    mainGroup.getChildren.add(tilesBoardContainer)

    _controlPanel.onResetPressed {
      this.resetClicked()
    }

    _controlPanel.onShufflePressed {
      this.shuffleClicked()
    }

    _controlPanel.onSizeChange { newSize =>
      this.newSizeSelected(newSize)
    }

    tilesBoardContainer.setOnKeyPressed(new EventHandler[KeyEvent] {
      def handle(event: KeyEvent): Unit = {
        val maybeMatch = event.getCode match {
          case KeyCode.UP => Some(Up)
          case KeyCode.DOWN => Some(Down)
          case KeyCode.LEFT => Some(Left)
          case KeyCode.RIGHT => Some(Right)
          case _ => None
        }
        maybeMatch.foreach { arrowKey =>
          arrowKeyPressed(arrowKey)
          event.consume()
        }
      }
    })

    setupWithGroup(mainGroup)
  }

  def setMovesCount(newCount: Int): Unit = {
    _controlPanel.setMovesCount(newCount)
  }

  def selectGridSize(newSize: GridSize): Unit = {
    _controlPanel.selectGridSize(newSize)
  }

  def setPuzzleView(puzzleView: PuzzleView): Unit = {
    puzzleView match {
      case jfxView: JFXPuzzleView => setPuzzleView(jfxView)
    }
  }

  def setPuzzleView(puzzleView: JFXPuzzleView): Unit = {
    val grpChildren = tilesBoardContainer.getChildren
    grpChildren.clear()
    grpChildren.add(puzzleView)
  }

  def show(): Unit = {
    window.show()
  }

  private def setupWithGroup(mainGroup: Parent): Unit = {
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
      def handle(event: WindowEvent): Unit = {
        Platform.exit()
      }
    })
  }
}
