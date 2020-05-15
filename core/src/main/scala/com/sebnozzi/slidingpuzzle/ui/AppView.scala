package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.structs.GridSize
import com.sebnozzi.slidingpuzzle.ui.keys.ArrowKey

trait AppView {

  private var shuffleCallback: Option[() => Unit] = None
  private var resetCallback: Option[() => Unit] = None
  private var newSizeCallback: Option[GridSize => Unit] = None
  private var arrowKeyCallback: Option[ArrowKey => Unit] = None

  /**
   * Implement.
   * Called when setting a new puzzle-view
   */
  def setPuzzleView(puzzleView: PuzzleView): Unit

  /**
   * Implement.
   * Called when the moves count has to be updated
   */
  def setMovesCount(newCount: Int): Unit

  /**
   * Implement.
   * Called when a new grid-size was selected
   */
  def selectGridSize(newSize: GridSize): Unit

  /**
   * Implement.
   * Called when this view should be shown
   */
  def show(): Unit

  /**
   * Your code should call this
   * if "shuffle" clicked on the UI
   */
  def shuffleClicked(): Unit = {
    shuffleCallback foreach (callback => callback())
  }

  /**
   * Your code should call this
   * if "reset" clicked on the UI
   */
  def resetClicked(): Unit = {
    resetCallback foreach (callback => callback())
  }

  /**
   * Your code should call this
   * if a new size was selected on the UI
   */
  def newSizeSelected(newSize: GridSize): Unit = {
    newSizeCallback foreach (callback => callback(newSize))
  }

  /**
   * Your code should call this
   * if a key was pressed on the UI
   */
  def arrowKeyPressed(arrowKey: ArrowKey): Unit = {
    arrowKeyCallback foreach (callback => callback(arrowKey))
  }

  /**
   * Used to set callback (don't modify / call)
   */
  def onShuffleClicked(callback: => Unit): Unit = {
    shuffleCallback = Some(() => callback)
  }

  /**
   * Used to set callback (don't modify / call)
   */
  def onResetClicked(callback: => Unit): Unit = {
    resetCallback = Some(() => callback)
  }

  /**
   * Used to set callback (don't modify / call)
   */
  def onNewSizeSelected(callback: (GridSize) => Unit): Unit = {
    newSizeCallback = Some(callback)
  }

  /**
   * Used to set callback (don't modify / call)
   */
  def onArrowKeyPressed(callback: (ArrowKey) => Unit): Unit = {
    arrowKeyCallback = Some(callback)
  }

}