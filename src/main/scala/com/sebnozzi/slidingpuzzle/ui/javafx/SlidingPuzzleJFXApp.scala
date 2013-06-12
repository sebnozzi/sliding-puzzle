package com.sebnozzi.slidingpuzzle.ui.javafx

import javafx.application.Application
import javafx.scene.image.Image
import javafx.scene.layout.HBox
import javafx.stage.Stage
import com.sebnozzi.slidingpuzzle.model.Position
import javafx.scene.canvas.Canvas
import javafx.scene.layout.VBox
import com.sebnozzi.slidingpuzzle.model.Game
import com.sebnozzi.slidingpuzzle.model.Tile
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent

class SlidingPuzzleJFXApp extends Application {

  val columns = 3
  val rows = 2

  val game = new Game(columns, rows)
  val hiddenTile = game.tiles.last

  var controlPanel: ControlPanel = _
  var tilesBoard: TilesBoard = _

  lazy val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  override def start(mainWindow: Stage) {
    val gameWindow = new GameWindowWrapper(mainWindow, img, columns, rows)

    controlPanel = gameWindow.controlPanel
    tilesBoard = gameWindow.tilesBoard

    gameWindow.onKeyPressed { keyEvent =>
      keyPressed(keyEvent)
    }

    controlPanel.onShufflePressed {
      game.reset()
      hiddenTile.makeHidden()
      game.makeRandomMove(times = 300)
      tilesBoard.requestFocus()
    }

    controlPanel.onResetPressed {
      game.reset()
      tilesBoard.requestFocus()
    }

    game.onMovesCountChange {
      updateMovesCount()
    }

    game.onGameSolved {
      hiddenTile.makeVisible()
    }

    game.tiles.zip(tilesBoard.tiles).foreach {
      case (modelTile: Tile, uiTile: TileNode) => {
        modelTile.onTileMoved {
          uiTile.moveTileTo(modelTile.currentPosition, animate = true)
        }
        modelTile.onVisibilityChange { toVisible =>
          if (toVisible)
            uiTile.makeVisible()
          else
            uiTile.makeHidden()
        }
        uiTile.onMousePressed {
          modelTile.moveToEmptySlot()
        }
      }
    }

    updateMovesCount()
    mainWindow.show()
  }

  def updateMovesCount() {
    controlPanel.setMovesCount(game.movesDone)
  }

  def keyPressed(keyEvent: KeyEvent) {
    var matchFound = true
    val tileToMove: Option[Tile] = (keyEvent.getCode() match {
      case KeyCode.UP => { hiddenTile.tileBelow }
      case KeyCode.DOWN => { hiddenTile.tileAbove }
      case KeyCode.LEFT => { hiddenTile.tileRight }
      case KeyCode.RIGHT => { hiddenTile.tileLeft }
      case _ => { matchFound = false; None }
    })

    tileToMove.map { _.moveToEmptySlot() }

    if (matchFound) {
      keyEvent.consume()
    }
  }

}
