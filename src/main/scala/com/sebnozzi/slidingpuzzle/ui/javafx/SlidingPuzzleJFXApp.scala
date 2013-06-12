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

class SlidingPuzzleJFXApp extends Application {

  val columns = 4
  val rows = 3
  val game = new Game(columns, rows)
  val hiddenTile = game.tiles.last
  var controlPanel: ControlPanel = _
  var tilesBoard: TilesBoard = _

  val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  override def start(mainWindow: Stage) {
    val gameWindow = new GameWindowWrapper(mainWindow, img, columns, rows)

    controlPanel = gameWindow.controlPanel
    tilesBoard = gameWindow.tilesBoard

    controlPanel.onShufflePressed {
      game.reset()
      hiddenTile.makeHidden()
      game.makeRandomMove(times = 300)
    }
    controlPanel.onResetPressed {
      game.reset()
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

}
