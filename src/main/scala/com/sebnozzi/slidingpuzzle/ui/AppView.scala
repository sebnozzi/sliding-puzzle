package com.sebnozzi.slidingpuzzle.ui

trait AppView {

  private var shuffleCallback: Option[() => Unit] = None

  def shuffleClicked() {
    if(shuffleCallback.isDefined)
      shuffleCallback.get()
  }

  def onShuffleClicked(callback: => Unit) {
    shuffleCallback = Some(callback _)
  }

}