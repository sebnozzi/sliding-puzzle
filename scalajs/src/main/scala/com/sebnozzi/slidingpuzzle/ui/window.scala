package com.sebnozzi.slidingpuzzle.ui

import scala.scalajs.js

trait JsSize extends js.Object {
  def cols: js.Number = ???
  def rows: js.Number = ???
}

trait JsUIController extends js.Object {
  def imageLoaded() = ???

  def setupGrid(cols: js.Number, rows: js.Number) = ???
  def findTile(tileId: js.String): js.Object = ???

  def getTileIds(): js.Array[String] = ???
  def getTileWidth(): js.Number = ???
  def getTileHeight(): js.Number = ???
//  def getSelectedSize(): JsSize = ???
//  def setSelectedSize(cols: js.Number, rows: js.Number) = ???

  def onTileClicked(callback: js.Function1[String, js.Object]) = ???
}

trait HasJsController {
  def log(msg: String) =
    js.Dynamic.global.console.log(msg)
  def jsController: JsUIController =
    js.Dynamic.global.window.jsUIController.asInstanceOf[JsUIController]
}