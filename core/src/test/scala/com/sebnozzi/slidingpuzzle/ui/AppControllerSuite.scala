package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import org.scalatest.FunSuite

class AppControllerSuite extends FunSuite {

  def mockAppView = new AppView() {
    override def setPuzzleView(puzzleView: PuzzleView) = {}
    override def setMovesCount(newCount: Int) {}
    override def selectGridSize(newSize: GridSize) {}
    override def show() {}
  }

  def mockPuzzleView = new PuzzleView {
    override def requestFocus() {}
    override val tileViews: List[TileView] = List[TileView]()
  }

  test("AppView is created") {
    var called = false
    val appController = new AppController() {
      override def createAppView(): AppView = {
        called = true
        mockAppView
      }
      override def createPuzzleView(gridSize: GridSize): PuzzleView = {
        mockPuzzleView
      }
    }
    appController.start()
    assert(called, "Should have created app-view")
  }

  test("PuzzleView is created") {
    var called = false
    val appController = new AppController() {
      override def createAppView(): AppView = {
        mockAppView
      }
      override def createPuzzleView(gridSize: GridSize): PuzzleView = {
        called = true
        mockPuzzleView
      }
    }
    appController.start()
    assert(called, "Should have created puzzle-view")
  }

  test("new puzzle is created after size change, hooks work") {
    pending
  }

  test("hidden tile becomes visible after puzzle won") {
    pending
  }

  test("shuffling works") {
    pending
  }

  test("shuffling does not move tiles with animation") {
    pending
  }

  test("reset works") {
    pending
  }

  test("update on move-count works") {
    pending
  }

}
