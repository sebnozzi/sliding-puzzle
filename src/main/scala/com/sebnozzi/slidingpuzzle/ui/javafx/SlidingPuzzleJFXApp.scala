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
import com.sebnozzi.slidingpuzzle.model.GridSize

class SlidingPuzzleJFXApp extends Application {

  private var _game: Game = _

  private var _gameWindow: GameWindowWrapper = _
  private var _tilesBoard: TilesBoard = _

  lazy val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  private def tilesBoard = _tilesBoard
  private def game = _game
  private def hiddenTile = game.tiles.last
  
  override def start(mainWindow: Stage) {
    val initialSize = GridSize(3, 2)

    _gameWindow = makeGameWindow(mainWindow, initialSize)

    setupGame(initialSize)

    mainWindow.show()
  }

  private def makeGameWindow(mainWindow: Stage, gridSize: GridSize) = {
    val gameWindow = new GameWindowWrapper(mainWindow)
    gameWindow.selectGridSize(gridSize)

    gameWindow.onKeyPressed { keyEvent =>
      keyPressed(keyEvent)
    }

    gameWindow.onShufflePressed {
      hiddenTile.makeHidden()
      game.shuffle()
      tilesBoard.requestFocus()
    }

    gameWindow.onResetPressed {
      game.reset()
      tilesBoard.requestFocus()
    }

    gameWindow.onSizeSelectionChange { newSize =>
      setupGame(newSize)
    }

    gameWindow
  }

  private def setupGame(gridSize: GridSize) {
    _game = new Game(gridSize.columns, gridSize.rows)

    _tilesBoard = new TilesBoard(img, gridSize.columns, gridSize.rows)
    _gameWindow.setTilesBoard(_tilesBoard)

    game.tiles.zip(tilesBoard.tiles).foreach {
      case (modelTile: Tile, uiTile: TileNode) => {
        bindUiAndModelTiles(uiTile, modelTile)
      }
    }

    game.onMovesCountChange {
      updateMovesCount()
    }

    game.onGameSolved {
      hiddenTile.makeVisible()
    }

    updateMovesCount()
  }

  private def updateMovesCount() {
    _gameWindow.setMovesCount(game.movesDone)
  }

  private def bindUiAndModelTiles(uiTile: TileNode, modelTile: Tile) {
    modelTile.onTileMoved {
      uiTile.moveTileTo(modelTile.currentPosition, animate = true)
    }
    modelTile.onVisibilityChange { toVisible =>
      if (toVisible) {
        uiTile.makeVisible(animate = modelTile.game.isSolved)
      } else {
        uiTile.makeHidden()
      }
    }
    uiTile.onMousePressed {
      modelTile.moveToEmptySlot()
    }
  }

  private def keyPressed(keyEvent: KeyEvent) {
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
