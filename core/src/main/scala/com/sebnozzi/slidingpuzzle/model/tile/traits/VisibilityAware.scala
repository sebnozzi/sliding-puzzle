package com.sebnozzi.slidingpuzzle.model.tile.traits

trait VisibilityAware {

  private var _visibilityCallback: Option[(Boolean) => Unit] = None

  def onVisibilityChange(callback: (Boolean) => Unit) {
    _visibilityCallback = Some(callback)
  }

  def visibilityChanged(toVisible: Boolean) {
    _visibilityCallback foreach (callback => callback(toVisible))
  }

}