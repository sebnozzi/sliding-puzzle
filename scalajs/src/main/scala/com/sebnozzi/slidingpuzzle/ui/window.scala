package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js

trait JsSize extends js.Object {
  def cols:js.Number = ???
  def rows:js.Number = ???
}

trait JsUIController extends js.Object {
  def imageLoaded() = ???
  
  def setupGrid(cols: js.Number, rows: js.Number) = ???
  def setMovesCount(newCount: js.Number) = ???
  def makeTileVisible(tileId: String, animate: Boolean) = ???
  def makeTileHidden(tileId: String) = ???
  def moveTileTo(tileId: String, col: js.Number, row: js.Number, animate: js.Boolean) = ???

  def getTileIds(): js.Array[String] = ???
  def getSelectedSize(): JsSize = ???

  def setSelectedSize(cols: js.Number, rows: js.Number) = ???

  def onTileClicked(callback: js.Function1[String, js.Object]) = ???
  def onSizeChanged(callback: js.Function2[js.Number, js.Number, js.Object]) = ???
  def onShufflePressed(callback: js.Function) = ???
  def onResetPressed(callback: js.Function) = ???
}

trait HasJsController {
  def log(msg: String) =
    js.Dynamic.global.console.log(msg)
  def jsController: JsUIController =
    js.Dynamic.global.window.jsUIController.asInstanceOf[JsUIController]
}