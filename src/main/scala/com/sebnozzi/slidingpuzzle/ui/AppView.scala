package com.sebnozzi.slidingpuzzle.ui

trait AppView {

  private var shuffleCallback: Option[() => Unit] = None
  private var resetCallback: Option[() => Unit] = None

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

}