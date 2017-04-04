package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.{Puzzle, PuzzleTile}
import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.ui.keys.ArrowKey

abstract class AppController() {

  private var puzzle: Puzzle = _

  private var appView: AppView = _
  private var puzzleView: PuzzleView = _

  private def hiddenTile = puzzle.tiles.last

  def createAppView(): AppView

  def createPuzzleView(gridSize: GridSize): PuzzleView

  /**
   * After creating the controller, you should call
   * this method to get the application running.
   */
  def start() {
    val initialGridSize = GridSize(3, 3)
    appView = createAppView()
    setupAppView(appView, initialGridSize)
    setupGame(initialGridSize)
    appView.show()
  }

  private def setupAppView(appView: AppView, gridSize: GridSize) = {
    appView.selectGridSize(gridSize)

    appView.onArrowKeyPressed { arrowKey =>
      arrowKeyPressed(arrowKey)
    }

    appView.onShuffleClicked {
      puzzle.setHiddenTile(hiddenTile)
      puzzle.shuffle()
      puzzleView.requestFocus()
    }

    appView.onResetClicked {
      puzzle.reset()
      puzzleView.requestFocus()
    }

    appView.onNewSizeSelected { newSize =>
      setupGame(newSize)
    }

    appView
  }

  private def setupGame(gridSize: GridSize) {
    puzzle = new Puzzle(gridSize)

    puzzleView = createPuzzleView(gridSize)

    appView.setPuzzleView(puzzleView)

    puzzle.tiles.zip(puzzleView.tileViews).foreach {
      case (modelTile: PuzzleTile, uiTile: TileView) => {
        bindUiAndModelTiles(uiTile, modelTile)
      }
    }

    puzzle.onMovesCountChange {
      updateMovesCount()
    }

    puzzle.onSolved {
      puzzle.clearHiddenTile()
    }

    updateMovesCount()
  }

  private def updateMovesCount() {
    appView.setMovesCount(puzzle.movesDone)
  }

  private def bindUiAndModelTiles(tileView: TileView, modelTile: PuzzleTile) {
    modelTile.onPositionChange { shuffling: Boolean =>
      tileView.moveTileTo(modelTile.currentPosition, animate = !shuffling)
    }
    modelTile.onVisibilityChange { toVisible =>
      if (toVisible) {
        tileView.makeVisible(animate = modelTile.puzzle.isSolved)
      } else {
        tileView.makeHidden()
      }
    }
    tileView.onMousePressed {
      modelTile.moveToEmptySlot()
    }
  }

  private def arrowKeyPressed(arrowKey: ArrowKey) {
    import com.sebnozzi.slidingpuzzle.ui.keys.{Down, Left, Right, Up}
    val tileToMove: Option[PuzzleTile] = arrowKey match {
      case Up => { hiddenTile.tileBelow }
      case Down => { hiddenTile.tileAbove }
      case Left => { hiddenTile.tileRight }
      case Right => { hiddenTile.tileLeft }
    }

    tileToMove.map { _.moveToEmptySlot() }
  }

}
