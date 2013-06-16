package com.sebnozzi.slidingpuzzle.ui

trait AppView {

  private var shuffleCallback: Option[() => Unit] = _

  def shuffleClicked() {
    shuffleCallback.get()
  }

  def onShuffleClicked(callback: => Unit) {
    shuffleCallback = Some(callback _)
  }

}