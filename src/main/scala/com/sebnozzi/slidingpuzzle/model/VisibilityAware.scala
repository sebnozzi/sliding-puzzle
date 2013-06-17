package com.sebnozzi.slidingpuzzle.model

trait VisibilityAware {

  private var _visibilityCallback: Option[(Boolean) => Unit] = None

  def onVisibilityChange(callback: (Boolean) => Unit) {
    _visibilityCallback = Some(callback)
  }

  def visibilityChanged(toVisible: Boolean) {
    if (_visibilityCallback.isDefined)
      _visibilityCallback.get(toVisible)
  }

}