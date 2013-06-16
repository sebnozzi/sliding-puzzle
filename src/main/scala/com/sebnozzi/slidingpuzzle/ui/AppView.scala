package com.sebnozzi.slidingpuzzle.ui

import com.sebnozzi.slidingpuzzle.model.GridSize

trait AppView {

  private var shuffleCallback: Option[() => Unit] = None
  private var resetCallback: Option[() => Unit] = None
  private var newSizeCallback: Option[(GridSize) => Unit] = None

  def shuffleClicked() {
    if (shuffleCallback.isDefined)
      shuffleCallback.get()
  }

  def onShuffleClicked(callback: => Unit) {
    shuffleCallback = Some(callback _)
  }

  def resetClicked() {
    if (resetCallback.isDefined)
      resetCallback.get()
  }

  def onResetClicked(callback: => Unit) {
    resetCallback = Some(callback _)
  }

  def onNewSizeSelected(callback: (GridSize) => Unit) {
    newSizeCallback = Some(callback)
  }

  def newSizeSelected(newSize: GridSize) {
    if(newSizeCallback.isDefined)
      newSizeCallback.get(newSize)
  }

}