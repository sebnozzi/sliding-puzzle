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
import com.sebnozzi.slidingpuzzle.ui.ArrowKey
import com.sebnozzi.slidingpuzzle.ui._
import com.sebnozzi.slidingpuzzle.ui.javafx.utils.ImageSlicer

class SlidingPuzzleJFXApp extends Application {

  private var _game: Game = _

  private var _gameWindow: JFXAppView = _
  private var _puzzleView: PuzzleView = _

  lazy val img = {
    val inputStream = this.getClass().getResourceAsStream("/2322324186_ca41fba641_o.jpg")
    new Image(inputStream)
  }

  private def puzzleView = _puzzleView
  private def game = _game
  private def hiddenTile = game.tiles.last

  override def start(mainWindow: Stage) {
    val initialSize = GridSize(3, 2)

    _gameWindow = makeGameWindow(mainWindow, initialSize)

    setupGame(initialSize)

    mainWindow.show()
  }

  private def makeGameWindow(mainWindow: Stage, gridSize: GridSize) = {
    val gameWindow = new JFXAppView(mainWindow)
    gameWindow.selectGridSize(gridSize)

    gameWindow.onArrowKeyPressed { arrowKey =>
      arrowKeyPressed(arrowKey)
    }

    gameWindow.onShuffleClicked {
      hiddenTile.makeHidden()
      game.shuffle()
      puzzleView.requestFocus()
    }

    gameWindow.onResetClicked {
      game.reset()
      puzzleView.requestFocus()
    }

    gameWindow.onNewSizeSelected { newSize =>
      setupGame(newSize)
    }

    gameWindow
  }

  private def setupGame(gridSize: GridSize) {
    val _gridSize = gridSize
    _game = new Game(gridSize.columns, gridSize.rows)

    _puzzleView = new JFXPuzzleView(img, gridSize)
    
    _gameWindow.setPuzzleView(_puzzleView)

    game.tiles.zip(puzzleView.tileViews).foreach {
      case (modelTile: Tile, uiTile: TileView) => {
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

  private def bindUiAndModelTiles(tileView: TileView, modelTile: Tile) {
    modelTile.onTileMoved {
      tileView.moveTileTo(modelTile.currentPosition, animate = true)
    }
    modelTile.onVisibilityChange { toVisible =>
      if (toVisible) {
        tileView.makeVisible(animate = modelTile.game.isSolved)
      } else {
        tileView.makeHidden()
      }
    }
    tileView.onMousePressed {
      modelTile.moveToEmptySlot()
    }
  }

  private def arrowKeyPressed(arrowKey: ArrowKey) {
    import com.sebnozzi.slidingpuzzle.ui.{Up,Down,Left,Right}
    val tileToMove: Option[Tile] = arrowKey match {
      case Up => { hiddenTile.tileBelow }
      case Down => { hiddenTile.tileAbove }
      case Left => { hiddenTile.tileRight }
      case Right => { hiddenTile.tileLeft }
    }

    tileToMove.map { _.moveToEmptySlot() }
  }

}
